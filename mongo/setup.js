connection = new Mongo();
db = connection.getDB('local');
db.createUser({user: 'dev', pwd: 'dev', roles: ['dbOwner']});