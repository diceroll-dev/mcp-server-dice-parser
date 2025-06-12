# MCP Server - Dice Parser

This is an example MCP Server that uses Spring AI that will parse dice notation and return results.

This version is using stdio, as to more easily integrate with existing clients.

## Build it

./mvnw verify

## Configure your client

To configure your client, open up it's JSON config file (or equivalent), for example, Claude's config file can be found at `~/Library/Application\ Support/Claude/claude_desktop_config.json` on macOS. Add an entry for the MCP server:

```json
{
  "mcpServers": {
    "dice-parser": {
      "command": "java",
      "args": [
        "-jar",
        "<absolute-path-to-project-directory>/target/mcp-roller-0.0.1-SNAPSHOT.jar",
        "--logging.file.name=<log-dir>/mcp-dice-parser.log"
      ]
    }
  }
}
```

> [!NOTE]
> Any time you change this file, you must restart your client.

## Have fun!

Example prompt:

```text
Let's play a roll playing game, you are the GM, and I'll play as an entry level rouge.

You create the character sheet for me, assign weapons, etc.

Our first encounter should be simple, like fighting a kobold.
```

