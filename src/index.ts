import { createServer } from "./server.js";

function startServer() {
    const port = 3000;

    const server = createServer();
    server.listen(port, () => console.log(`server listening on port ${port}`));
}

startServer();
