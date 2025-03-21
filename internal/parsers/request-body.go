package parsers

import (
	"encoding/json"
	"fmt"
	"strings"

	"go.uber.org/zap"
)

type Example struct {
	Xama string `json:"xama"`
	Num  int64  `json:"num"`
}

// POST /example/my/path/varValue?my-query=queuqeuqeri&anther=another HTTP/1.1
// Accept: application/json, text/plain, */*
// Content-Type: application/json
// Head1: Head1-Value
// head2: another-heade
// Authorization: Bearer TOKEN_JWT_OR_SOMEOTHER
// request-start-time: 1724529808312
// User-Agent: axios/1.7.2
// Content-Length: 15
// Accept-Encoding: gzip, compress, deflate, br
// Host: localhost:3000
// Connection: close

// {"foo":"bar"}

type HTTPRequest struct {
	Method      string
	URL         string
	HTTPVersion string
	Headers     map[string]string
	Body        map[string]any
}

func StrToHttpReq(buf []byte, logger *zap.SugaredLogger) (HTTPRequest, error) {
	req := string(buf)
	var parsed HTTPRequest
	reqLines := strings.Split(req, "\n")

	metadata := strings.Split(reqLines[0], " ")
	if len(metadata) != 3 {
		return HTTPRequest{},
			fmt.Errorf("the first line of a request is supposed to have method, " +
				"url and http version all divided by a whitespace")
	}

	parsed.Method = metadata[0]
	parsed.URL = metadata[1]
	parsed.HTTPVersion = metadata[2]

	parsed.Headers = make(map[string]string)
	for _, header := range reqLines[1 : len(reqLines)-2] {
		key, val, _ := strings.Cut(header, ": ")
		parsed.Headers[key] = val
	}

	parsed.Body = StrToHttpReqBody(buf, logger)
	logger.Info("HTTP request parsed")

	return parsed, nil
}

// only parses json
func StrToHttpReqBody(reqBody []byte, logger *zap.SugaredLogger) map[string]any {
	logger.Info(reqBody)
	var example Example
	var err error
	err = json.Unmarshal(reqBody, &example)
	if err != nil {
		return nil
	}

	return nil
}
