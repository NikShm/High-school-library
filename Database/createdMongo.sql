db.season_tickets.insertOne(
    {
        id_user: 1,
        type: "Student",
        name: "X",
        surname: "Y",
        book: "A",
        author: "B",
        date_of_issue: "01.08.04",
        return_date: "01.10.04",
        status:"Здано"
    });
db.season_tickets.drop();