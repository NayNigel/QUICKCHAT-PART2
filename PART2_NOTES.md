# Part 2 Notes

This application allows a user to send messages after logging in successfully.

The program uses a menu system that allows the user to:
1. Send messages
2. View recently sent messages (coming soon)
3. Quit the application

A for loop is used to allow the user to enter the number of messages they selected.

Each message includes:
- Message ID
- Message Hash
- Recipient number
- Message content

Messages can be:
- Sent
- Disregarded
- Stored in a JSON file

The message hash is generated using:
- First 2 digits of message ID
- Message number
- First and last word of the message (uppercase)
