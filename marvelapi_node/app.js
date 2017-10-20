var express = require('express');
var app = express();

var listData = {
    "data": {
        "results": [{
                "id": 22582,
                "digitalId": 0,
                "title": "Civil War (Hardcover)",
                "issueNumber": 0,
                "variantDescription": "",
                "description": "The landscape of the Marvel Universe is changing, and it's time to choose: Whose side are you on? A conflict has been brewing from more than a year, threatening to pit friend against friend, brother against brother - and all it will take is a single misstep to cost thousands their lives and ignite the fuse! As the war claims its first victims, no one is safe as teams, friendships and families begin to fall apart. The crossover that rewrites the rules, Civil War stars Spider-Man, the New Avengers, the Fantastic Four, the X-Men and the entirety of the Marvel pantheon! Collecting CIVIL WAR #1-7, MARVEL SPOTLIGHT: CIVIL WAR and CIVIL WAR SCRIPT BOOK.\r<br>Rated T+ ...$39.99\r<br>ISBN: 978-0-7851-2178-7\r<br>Trim size: oversized\r<br>",
                "modified": "2013-03-18T15:33:12-0400",
                "isbn": "978-0-7851-2178-7",
                "upc": "5960612178-00111",
                "diamondCode": "AUG082435",
                "ean": "9780785 121787 53999",
                "issn": "",
                "format": "Hardcover",
                "pageCount": 512
            },
            {
                "id": 37500,
                "digitalId": 0,
                "title": "Marvels Vol. 1 (1994) #4",
                "issueNumber": 4,
                "variantDescription": "",
                "description": null,
                "modified": "2014-05-08T12:18:41-0400",
                "isbn": "",
                "upc": "",
                "diamondCode": "",
                "ean": "",
                "issn": "",
                "format": "Digital Comic",
                "pageCount": 0,
                "textObjects": [],
                "resourceURI": "http://gateway.marvel.com/v1/public/comics/37500"
            },
            {
                "id": 16244,
                "digitalId": 0,
                "title": "Cap Transport (2005) #5",
                "issueNumber": 5,
                "variantDescription": "",
                "description": null,
                "modified": "-0001-11-30T00:00:00-0500",
                "isbn": "",
                "upc": "",
                "diamondCode": "",
                "ean": "",
                "issn": "",
                "format": "Comic",
                "pageCount": 0,
                "textObjects": [],
                "resourceURI": "http://gateway.marvel.com/v1/public/comics/16244"
            }
        ]
    }
};

app.get('/', function (req, res) {
    res.send("Olá tudo bem? Digite comics para listar algo");

});

app.get('/comics', function (req, res) {
    res.json(listData);
});

app.get('/comics/:id', function (req, res) {

    var comic = getComicById(req.params.id);

    if (comic == null || comic == undefined) {
        res.status(404);
    }
    res.json(comic);

});

function getComicById(id) {
    for (var i = 0; i < listData.data.results.length; i++) {
        if (listData.data.results[i].id == id) return listData.data.results[i];
    }
};

app.listen(3000, function () {
    console.log('Example app listening on port 3000!');
});