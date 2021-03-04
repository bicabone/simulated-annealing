connection = new Mongo();
db = connection.getDB("local-dev");
db.createUser({
    user: "developer", pwd: "developer", roles: [{
        role: "readWrite",
        db: "local-dev"
    }]
});