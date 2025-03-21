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

	str := ""
	extra := ""
	for {
		buff := make([]byte, 8)
		cout, err := file.Read(buff)
		if err != nil {
			os.Exit(0)
		}

		if extra != "" {
			str = extra + str
			extra = ""
		}

		isNewLine := false
		for _, cha := range string(buff[:cout]) {
			if !isNewLine {
				if cha != '\n' {
					str += string(cha)
				} else {
					fmt.Printf("read: %s\n", str)
					str = ""
					isNewLine = true
				}
			} else {
				extra += string(cha)
			}
		}

	}
}
