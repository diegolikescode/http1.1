package socketport

import (
	"fmt"
	"net"
	"os"
)

func StartServer() {
	port := ":6969"
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

	c.Write([]byte("Hello from TCP\n"))
}
