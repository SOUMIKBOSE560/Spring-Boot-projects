<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>File Uploader</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }

        .container {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
            border-radius: 5px;
        }

        h1 {
            text-align: center;
        }

        label {
            font-weight: bold;
        }

        input[type="file"] {
            display: block;
            margin-top: 10px;
        }

        button {
            display: block;
            margin-top: 10px;
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        #data {
            margin-top: 20px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>File Uploader</h1>
        <form method="post" enctype="multipart/form-data">
            <label for="fileInput">Select Files:</label>
            <input type="file" id="fileInput" name="files[]" multiple>
            <button type="submit" name="submit">Upload Files</button>
          
        </form>
        <div id="data"></div>
    </div>

    </body>

    
    <script>
   
   


//    const url = "http://localhost:6060/admin/upload";
//  const form = document.querySelector("form");

// form.addEventListener("submit", (e) => {
//   e.preventDefault();

//   const files = document.querySelector("[type=file]").files;
//   const formData = new FormData();

//   for (let i = 0; i < files.length; i++) {
//     let file = files[i];
//     formData.append("file", file);
//     formData.append("upload_preset", "docs_upload_example_us_preset");

//     console.log(formData);

//     fetch(url, {
//       method: "POST",
//       body: formData
//     })
//       .then((response) => {
//         return response.text();
//       })
//       .then((data) => {
//         document.getElementById("data").innerHTML += data;
//       });
//   }
// });



const url = "http://localhost:6060/admin/uploadMulti";
const form = document.querySelector("form");

form.addEventListener("submit", (e) => {
  e.preventDefault();

  const files = document.querySelector("[type=file]").files;
  const formData = new FormData();

  for (let i = 0; i < files.length; i++) {
    let file = files[i];
    formData.append("files", file); // Use "files" as the parameter name
  }

  // No need to send "upload_preset" here, as it seems specific to your application

  fetch(url, {
    method: "POST",
    body: formData,
  })
    .then((response) => {
      return response.text();
    })
    .then((data) => {
      document.getElementById("data").innerHTML += data;
    });
});


    </script>
    </body>
    </html>
    
    