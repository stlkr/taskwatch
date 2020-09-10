<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">TaskWatch</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
  
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <a class="nav-link" href="/">List<span class="sr-only">(current)</span></a>
        </li>

        <li class="nav-item active">
            <a class="nav-link" href="/edit/new">Add<span class="sr-only">(current)</span></a>
        </li>
      </ul>
      <form class="form-inline my-2 my-lg-0" method="GET" action="/list">
        <input class="form-control mr-sm-2" type="text" name="query" placeholder="Search" aria-label="Search" value="${query}">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
      </form>

      <form class="form-inline my-2 my-lg-0" method="GET" action="/logout">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Log Out</button>
      </form>
    </div>
  </nav>