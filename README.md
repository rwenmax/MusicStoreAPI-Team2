<div id="top"></div>
<div align="center">
   <a href="https://github.com/rwenmax/MusicStoreAPI-Team1">
    <img src="images/api.png" alt="Logo" width="250" height="250">
  </a>
    <h1 align= "center">Music Store API</h1>
</div>
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
    </li>
    <li><a href="#checklist">Checklist</a></li>
    <li>
      <a href="#Entities">Entities</a>
      <ul>
        <li><a href="#track">Track</a></li>
      </ul>
	    <ul>
        <li><a href="#customer">Customer</a></li>
      </ul>
    </li>
      <li><a href="#tools-and-Frameworks">Tools and Frameworks</a></li>
	<li><a href="#credits-and-contact">Credits and Contact</a></li>
	  <li><a href="#license">License</a></li>
  </ol>
</details>

## About The Project

>Create an API that can manipulate a Music Store Database, with retrieving, updating and inserting data from and into the tables in the database.
>
>Keep in mind when coding of SOLID principles, design patterns, Java standard practices, git conflicts when merging, JDBC configuration and OOP. 
>
>Add addtional functionality such as Token Access, advanced search functions and a few extra tables such as the Discount Table.

## Checklist

- [x] SOLID principles
- [x] Java standard practices
- [x] Use of git and sorting merging conflicts
- [x] Use of JDBC
- [x] Entities, Repositories and Controllers
- [x] Access Token Implementation
- [ ] Advanced Search Functions
- [x] Junit Testing

## Functions

This section demonstrates how to use requests for given entity. 

### **Track**

#### *GET all tracks*

URL endpoint: chinook/tracks

Response: list of all tracks 

```json
[
    {
        "id": 1,
        "name": "For Those About To Rock (We Salute You)",
        "albumId": 1,
        "mediaTypeId": 1,
        "genreId": 1,
        "composer": "Angus Young, Malcolm Young, Brian Johnson",
        "milliseconds": 343719,
        "bytes": 11170334,
        "unitPrice": 0.99
    },
    {
        "id": 2,
        "name": "Balls to the Wall",
        "albumId": 2,
        "mediaTypeId": 2,
        "genreId": 1,
        "composer": null,
        "milliseconds": 342562,
        "bytes": 5510424,
        "unitPrice": 0.99
    },
...
```



#### *GET track by ID*

URL endpoint: /chinook/tracks/{trackId}

Response: track for given ID

Example for track with ID: **100**

```json
{
    "id": 100,
    "name": "Out Of Exile",
    "albumId": 11,
    "mediaTypeId": 1,
    "genreId": 4,
    "composer": "Cornell, Commerford, Morello, Wilk",
    "milliseconds": 291291,
    "bytes": 9506571,
    "unitPrice": 0.99
}
```



#### *GET track by name*

URL endpoint: /chinook/tracks-by-name?name=Balls

Response: tracks that match name 

Example for track with name: **"Out of exile"**

```json
[
    {
        "id": 100,
        "name": "Out Of Exile",
        "albumId": 11,
        "mediaTypeId": 1,
        "genreId": 4,
        "composer": "Cornell, Commerford, Morello, Wilk",
        "milliseconds": 291291,
        "bytes": 9506571,
        "unitPrice": 0.99
    }
]
```



#### *GET track by album ID*

URL endpoint: /chinook/tracks-by-album/{albumId}

Response: list of tracks in given album

Example for album with ID: **9**

```json
[
    {
        "id": 77,
        "name": "Enter Sandman",
        "albumId": 9,
        "mediaTypeId": 1,
        "genreId": 3,
        "composer": "Apocalyptica",
        "milliseconds": 221701,
        "bytes": 7286305,
        "unitPrice": 0.99
    },
    {
        "id": 78,
        "name": "Master Of Puppets",
        "albumId": 9,
        "mediaTypeId": 1,
        "genreId": 3,
        "composer": "Apocalyptica",
        "milliseconds": 436453,
        "bytes": 14375310,
        "unitPrice": 0.99
    },
...
```



#### *GET track by artist ID*

URL endpoint: /chinook/tracks-by-artist/{artistId}

Response: tracks by artist 

Example for artist **AC/DC** with ID : **1**

```json
[
    {
        "id": 1,
        "name": "For Those About To Rock (We Salute You)",
        "albumId": 1,
        "mediaTypeId": 1,
        "genreId": 1,
        "composer": "Angus Young, Malcolm Young, Brian Johnson",
        "milliseconds": 343719,
        "bytes": 11170334,
        "unitPrice": 0.99
    },
    {
        "id": 6,
        "name": "Put The Finger On You",
        "albumId": 1,
        "mediaTypeId": 1,
        "genreId": 1,
        "composer": "Angus Young, Malcolm Young, Brian Johnson",
        "milliseconds": 205662,
        "bytes": 6713451,
        "unitPrice": 0.99
    },
...
```

<p align="right">(<a href="#top">back to top</a>)</p>

### **Customer**

#### *GET all customers*

URL endpoint: chinook/allcustomer

Response: list of all customers

```json
[
    {
        "id": 1,
        "firstName": "Luís",
        "lastName": "Gonçalves",
        "company": "Embraer - Empresa Brasileira de Aeronáutica S.A.",
        "address": "Av. Brigadeiro Faria Lima, 2170",
        "city": "São José dos Campos",
        "state": "SP",
        "country": "Brazil",
        "postalCode": "12227-000",
        "phone": "+55 (12) 3923-5555",
        "fax": "+55 (12) 3923-5566",
        "email": "luisg@embraer.com.br",
        "supportRepId": 4
    },
    {
        "id": 2,
        "firstName": "Leonie",
        "lastName": "Köhler",
        "company": null,
        "address": "Theodor-Heuss-Straße 34",
        "city": "Stuttgart",
        "state": null,
        "country": "Germany",
        "postalCode": "70174",
        "phone": "+49 0711 2842222",
        "fax": null,
        "email": "leonekohler@surfeu.de",
        "supportRepId": 6
    },
...
```



#### *GET customer by ID*

URL endpoint: /chinook/customer/{customerID}

Response: customer for given ID

Example for customer with ID: **10**

```json
{
    "id": 10,
    "firstName": "Eduardo",
    "lastName": "Martins",
    "company": "Woodstock Discos",
    "address": "Rua Dr. Falcão Filho, 155",
    "city": "São Paulo",
    "state": "SP",
    "country": "Brazil",
    "postalCode": "01007-010",
    "phone": "+55 (11) 3033-5446",
    "fax": "+55 (11) 3033-4564",
    "email": "eduardo@woodstock.com.br",
    "supportRepId": 4
}
```

#### *POST customer*

URL endpoint: /chinook/customer/add

Response: Inserts a new customer into the Customer Table

Example for customer in json format:
```json
{
    "firstName": "Eduardo",
    "lastName": "Martins",
    "company": "Woodstock Discos",
    "address": "Rua Dr. Falcão Filho, 155",
    "city": "São Paulo",
    "state": "SP",
    "country": "Brazil",
    "postalCode": "01007-010",
    "phone": "+55 (11) 3033-5446",
    "fax": "+55 (11) 3033-4564",
    "email": "eduardo@woodstock.com.br",
    "supportRepId": 4
}
```

#### *PUT customer*

URL endpoint: /chinook/customer/update/{customerID}

Response: Updates an existsing customer by ID

Example for customer of ID: 10, being updated with:
```json
{
    "firstName": "Eduardo",
    "lastName": "Martins",
    "company": "Woodstock Discos",
    "address": "Rua Dr. Falcão Filho, 155",
    "city": "São Paulo",
    "state": "SP",
    "country": "Brazil",
    "postalCode": "01007-010",
    "phone": "+55 (11) 3033-5446",
    "fax": "+55 (11) 3033-4564",
    "email": "eduardo@woodstock.com.br",
    "supportRepId": 4
}
```

#### *GET customer by Email*

URL endpoint: /chinook/customer/email/{email}

Response: customer that matches the given email

Example for Email: "eduardo@woodstock.com.br":
```json
{
    "firstName": "Eduardo",
    "lastName": "Martins",
    "company": "Woodstock Discos",
    "address": "Rua Dr. Falcão Filho, 155",
    "city": "São Paulo",
    "state": "SP",
    "country": "Brazil",
    "postalCode": "01007-010",
    "phone": "+55 (11) 3033-5446",
    "fax": "+55 (11) 3033-4564",
    "email": "eduardo@woodstock.com.br",
    "supportRepId": 4
}
```
### **Invoice**

#### *GET all invoices*

URL endpoint: chinook/invoices

Response: list of all invoices

```json
[
  {
    "id": 1,
    "invoiceDate": "2009-01-01T00:00:00Z",
    "billingAddress": "Theodor-Heuss-Straße 34",
    "billingCity": "Stuttgart",
    "billingState": null,
    "billingCountry": "Germany",
    "billingPostalCode": "70174",
    "total": 1.98
  },
  {
    "id": 2,
    "invoiceDate": "2009-01-02T00:00:00Z",
    "billingAddress": "Ullevålsveien 14",
    "billingCity": "Oslo",
    "billingState": null,
    "billingCountry": "Norway",
    "billingPostalCode": "0171",
    "total": 3.96
  },
```

#### *GET invoice by ID*

URL endpoint: /chinook/invoice/{trackId}

Response: invoice for given ID

Example for invoice with ID: **11**

```json
{
  "id": 11,
  "invoiceDate": "2009-02-03T00:00:00Z",
  "billingAddress": "4 Chatham Street",
  "billingCity": "Dublin",
  "billingState": "Dublin",
  "billingCountry": "Ireland",
  "billingPostalCode": null,
  "total": 5.94
}
```

#### *POST invoice*

URL endpoint: /chinook/invoice/add

Response: Insert a new invoice into the invoice table

Example for invoice in json format:

```json
{
  "customerId": 2,
  "invoiceDate": "2009-02-03T00:00:00Z",
  "billingAddress": "3 Chatham Street",
  "billingCity": "Dublin",
  "billingState": "Dublin",
  "billingCountry": "Ireland",
  "billingPostalCode": null,
  "total": 5.94
}
```

#### *PUT invoice*

URL endpoint: /chinook/invoice/update

Response: Update a new invoice into the invoice table

Example for invoice in json format:

```json
{
  "customerId": 2,
  "invoiceDate": "2009-02-03T00:00:00Z",
  "billingAddress": "3 Chatham Street",
  "billingCity": "Dublin",
  "billingState": "Dublin",
  "billingCountry": "Ireland",
  "billingPostalCode": null,
  "total": 5.94
}
```

<p align="right">(<a href="#top">back to top</a>)</p>


---

## Credits and Contact

[Kamil](https://github.com/rwenmax) • [Konrad](https://github.com/KonradDlugosz) • [Talal](https://github.com/talal1998) • [Alex](https://github.com/alexsusanu) • [Anthony](https://github.com/MagerXser) • [Ed](https://github.com/EdBencito) • [Nikolas](https://github.com/Moodhunter34) • [Ishmael](https://github.com/ishariffSG) • [Mihai](https://github.com/Ascerte)  

---

## License

**Free**
