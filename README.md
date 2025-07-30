<!DOCTYPE html>
<html>
<head>
    <title>Image Gallery</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        .image-container {
            margin: 20px 0;
            text-align: center;
        }
        .image-container img {
            max-width: 100%;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .caption {
            margin-top: 8px;
            font-size: 14px;
            color: #555;
        }
    </style>
</head>
<body>
    <h1>My Image Gallery</h1>
    <div class="image-container">
        <img src="https://placehold.co/600x400" alt="Sample placeholder image" />
        <div class="caption">This is an example image hosted online</div>
    </div>
    
    <div class="image-container">
        <img src="images/my-image.jpg" alt="Description of local image" />
        <div class="caption">This would be an image from your repository's images folder</div>
    </div>
</body>
</html>
