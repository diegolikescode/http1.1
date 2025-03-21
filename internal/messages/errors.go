package messages

import "strconv"

func GetParserError() string {
	body := "<h1>Couldn't parse your request, try a default HTTP request<h1>"
	response := "HTTP/1.1 400 BAD REQUEST\r\n" +
		"Content-Type: text/html\r\n" +
		"Content-Length: " + strconv.Itoa(len(body)) + "\r\n" +
		"\r\n" +
		body

	return response
}
