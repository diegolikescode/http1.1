package main

import (
	"fmt"
	"io"
	"os"
)

func getLinesChannel(f io.ReadCloser) <-chan string {
	str := ""
	extra := ""
	for {
		buff := make([]byte, 8)
		cout, err := f.Read(buff)
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
				<-extra += string(cha)
			}
		}

	}
}

func main() {
	f, err := os.Open("messages.txt")
	if err != nil {
		os.Exit(1)
	}
	lines := make(chan string)
	go getLinesChannel(f)
}
