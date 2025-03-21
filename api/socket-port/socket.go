package socketport

import (
	"fmt"
	"net"
	"os"
	"strconv"

	"github.com/diegolikescode/http1.1/internal/messages"
	"github.com/diegolikescode/http1.1/internal/parsers"
	"go.uber.org/zap"
)

func StartServer() {
	devLogger, err := zap.NewDevelopment()
	if err != nil {
		panic("not able to create the logger with zap")
	}
	logger := devLogger.Sugar()

	port := ":3000"
	ln, err := net.Listen("tcp", port)
	if err != nil {
		logger.DPanic("Not able to create the tcp port")
		os.Exit(1)
	}

	logger.Info("start listening to connections")

	for {
		conn, err := ln.Accept()
		if err != nil {
			logger.Infoln("conn wasnt handled, err:", err)
			continue
		}
		go handleConn(conn, logger)
	}
}

func handleConn(c net.Conn, logger *zap.SugaredLogger) {
	defer c.Close()

	buf := make([]byte, 1024)
	n, er := c.Read(buf)
	if er != nil {
		logger.DPanic("not able to listen this request")
		body := "<h1>NOT SUPPORTED MESSAGE FORMAT, BAD REQUEST<h1>"
		response := "HTTP/1.1 400 BAD REQUEST\r\n" +
			"Content-Type: text/html\r\n" +
			"Content-Length: " + strconv.Itoa(len(body)) + "\r\n" +
			"\r\n" +
			body

		c.Write([]byte(response))
		return
	}

	incomingData := string(buf[:n])
	fmt.Println("\n" + incomingData + "\n" + "THIS IS THE END")

	body := "<h1>HELLO<h1>"
	response := "HTTP/1.1 400 BAD REQUEST\r\n" +
		"Content-Type: text/html\r\n" +
		"Content-Length: " + strconv.Itoa(len(body)) + "\r\n" +
		"\r\n" +
		body

	_, err := parsers.StrToHttpReq(buf[:n], logger)
	if err != nil {
		logger.DPanic("Request wasnt parsed, HTTP Request:", incomingData)
		c.Write([]byte(messages.GetParserError()))
		return
	}

	c.Write([]byte(response))
}
