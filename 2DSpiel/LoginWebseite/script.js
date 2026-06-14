// 1. Den Button über seine ID finden
const buttonA = document.getElementById("AccountErstellen");

// 2. Ein "Klick"-Ereignis hinzufügen
buttonA.addEventListener("click", function() {
   window.location.href = "./neuerAccount/index.php";
});
