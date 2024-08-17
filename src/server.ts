import net from "node:net";

export function createServer() {
    return net.createServer((socket) => {
        console.log("Conn Stablished");

        socket.on("data", (data) => {
            console.log("DATA");
            console.log(data.toString());

            const body = JSON.stringify({
                message: "Hello, this is a static response",
                data: {
                    id: 123,
                    name: "John Doe",
                    timestamp: new Date().toISOString(),
                },
            });

            // Create a static HTTP/1.1 response
            const contentLength = Buffer.byteLength(body);
            console.log(contentLength);
            const response =
                `HTTP/1.1 200 OK\r\n` +
                `Content-Type: application/json\r\n` +
                `Content-Length: 0\r\n`;

            // Write the response string back to the client
            socket.write(response);
            socket.end();
        });

        socket.on("end", () => {
            console.log("client disconnected");
        });

        socket.on("error", (err) => {
            console.log("Conn error:", err.message);
        });
    });
}
