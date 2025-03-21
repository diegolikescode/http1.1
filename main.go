package main

import (
	"fmt"
	"os"
)

func main() {
	// socketport.StartServer()
	file, err := os.Open("messages.txt")
	if err != nil {
		os.Exit(1)
	}

	for {
		buff := make([]byte, 8)
		cout, err := file.Read(buff)
		if err != nil {
			os.Exit(0)
		}

		fmt.Printf("read: %s\n", buff[:cout])
	}
}
