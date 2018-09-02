// Modal Image Gallery
function onClick(element) {
  document.getElementById("img01").src = element.src;
  document.getElementById("modal01").style.display = "block";
  var captionText = document.getElementById("caption");
  captionText.innerHTML = element.alt;
}

// Change style of navbar on scroll
window.onscroll = function() {myFunction()};
function myFunction() {
    var navbar = document.getElementById("myNavbar");
    if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
        navbar.className = "w3-bar" + " w3-card" + " w3-animate-top" + " w3-white";
    } else {
        navbar.className = navbar.className.replace(" w3-card w3-animate-top w3-white", "");
    }
}

// Used to toggle the menu on small screens when clicking on the menu button
function toggleFunction() {
    var x = document.getElementById("navDemo");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else {
        x.className = x.className.replace(" w3-show", "");
    }
}

function loadHideGallery() {
  var elToToggle = document.getElementsByClassName("hiddenGallery");
  var button = document.getElementById("galleryButton");
  var i;

  if (button.innerHTML === "LOAD MORE") {
    for (i = 0; i < elToToggle.length; i++) {
      elToToggle[i].style.display = "block";
      }
      button.innerHTML = "HIDE GALLERY"
  } else {
    for (i = 0; i < elToToggle.length; i++) {
      elToToggle[i].style.display = "none";
      }
      button.innerHTML = "LOAD MORE"
  }
}

function loadHideSkills() {
  var elToToggle = document.getElementsByClassName("hiddenSkills");
  var button = document.getElementById("skillsButton");
  var i;

  if (button.innerHTML === "LOAD MORE") {
    for (i = 0; i < elToToggle.length; i++) {
      elToToggle[i].style.display = "block";
      }
      button.innerHTML = "HIDE SKILLS"
  } else {
    for (i = 0; i < elToToggle.length; i++) {
      elToToggle[i].style.display = "none";
      }
      button.innerHTML = "LOAD MORE"
  }
}

//lightbox
// Open the Modal
function openModal() {
    document.getElementById('myModal').style.display = "block";
  }
  
  // Close the Modal
  function closeModal() {
    document.getElementById('myModal').style.display = "none";
  }
  
  var slideIndex = 1;
  console.log(slideIndex);
  
  // Next/previous controls
  function plusSlides(n) {
    showSlides(slideIndex += n);
  }
  
  // Thumbnail image controls
  function currentSlide(n) {
    showSlides(slideIndex = n);
  }
  
  function showSlides(n) {
    console.log('Current slide index '+n);
    var i;
    var slides = document.getElementsByClassName("mySlides");
    console.log(slides);
    var dots = document.getElementsByClassName("demo");
    console.log(dots);
    var captionText = document.getElementById("caption");
    console.log('Slodes length '+slides.length);
    console.log('Slodes index '+slideIndex);
    
    if (n > slides.length) {slideIndex = 1}
    if (n < 1) {slideIndex = slides.length}

    for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
    }
    console.log('Dots length '+dots.length);
    for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
    }
    console.log('slideIndex '+slideIndex);
    var temp = slideIndex-1;
    console.log('slideIndex-1 '+temp);
    console.log('Previous slide style '+slides[slideIndex-1].style.display);
    // console.log('Current slide style '+slides[slideIndex].style.display);
    
    slides[slideIndex-1].style.display = "block";
    dots[slideIndex-1].className += " active";
    captionText.innerHTML = dots[slideIndex-1].alt;
  }

  //hide show menu

  function toggleMenu() {
    var el = document.getElementById("navDemo");
    if (el.className.indexOf("showElement") == -1) {
        el.className += " showElement";
    } else {
        el.className = el.className.replace(" showElement", "");
    }
}