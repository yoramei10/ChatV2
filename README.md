# ChatV2

the server is using mongoDB and Kafka message.
the APIs:
login and create user,
logout and delete user,
publish new message,
get all users (only for admin)
get all messages (the DB is clear at each setup)
get all user names.

the create user, remove user and publish message APIs:
each of them make change in the DB and publish message to kafka.
the UI should listen to this topic (ChatTopic) and render the view as needed.

instructions to run and test the server side:
1. establish kafka
2. create topic with name : ChatTopic
3. update in the configuration file the url of the kafka (default localhost:9092)
4. run the project - the root is : ChatAppV2Application
5. run the postman collection to test the APIs (attached)
