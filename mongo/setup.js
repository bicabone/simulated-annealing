connection = new Mongo();
db = connection.getDB("local");
db.createUser({ user: "developer", pwd: "developer", roles: ["dbOwner"] });