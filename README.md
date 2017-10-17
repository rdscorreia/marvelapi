# POC MarvelAPI

Exemplo de uso das API's da Marvel

Tecnologias usadas:

 - Spring MVC 4 
 - Jersey 2.26
 - Jackson 2.75

Para o exemplo foram usados os seguintes endpoints:

 - GET /v1/public/comics/
 - GET /v1/public/comics/{comicId}
 - GET /v1/public/comics/{comicId}/creators
 - GET /v1/public/creators/{creatorId} 

 Para acesso aos endpoints da Marvel é necessário criar uma chave de acesso criptografada, link:
 https://marvel.com/signin?referer=https%3A%2F%2Fdeveloper.marvel.com%2Faccount
