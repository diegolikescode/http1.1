package socketport

import (
	"fmt"
	"net"
	"os"
)

func StartServer() {
	port := ":3000"
	ln, err := net.Listen("tcp", port)
	if err != nil {
		fmt.Println("Socket connection error", err)
		os.Exit(1)
	}

	fmt.Println("Listning on port", port)

	for {
		conn, err := ln.Accept()
		if err != nil {
			fmt.Println("Conn wasnt handled, ERROR", err)
			continue
		}
		go handleConn(conn)
	}
}

func handleConn(c net.Conn) {
	defer c.Close()

	buf := make([]byte, 1024)
	n, er := c.Read(buf)
	if er != nil {
		panic("FUCK")
	}

	incomingData := string(buf[:n])
	fmt.Println("\n" + incomingData)

	response := "HTTP/1.1 200 OK\r\n" +
		"Content-Type: text/plain\r\n" +
		"Content-Length: 13\r\n" +
		"\r\n" +
		"Hello, World!"

	c.Write([]byte(response))
}
