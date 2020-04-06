
var url = 'http://newsapi.org/v2/top-headlines?' +
          'country=us&' +
          'apiKey=760f6bb3dd0e43f1bef41c8cfd1d110b';
var req = new Request(url);
fetch(req)
  .then((resp) => resp.json())
    .then(function(response) {
        console.log(response.json());
    });
    .catch(function(error) {
        console.log(error);
    });