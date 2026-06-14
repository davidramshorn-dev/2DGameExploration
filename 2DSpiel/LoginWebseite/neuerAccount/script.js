// 1. Den Button über seine ID finden
const buttonB = document.getElementById("BereitsAccount");

// 2. Ein "Klick"-Ereignis hinzufügen
buttonB.addEventListener("click", function() {
   window.location.href = "../index.php";
});
