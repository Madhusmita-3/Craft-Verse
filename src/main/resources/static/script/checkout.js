// Function to create the bubbles in the background
function createBubbles() {
  const animationContainer = document.querySelector(".background-animation");
  for (let i = 0; i < 20; i++) {
    let bubble = document.createElement("div");
    bubble.classList.add("bubble");
    let size = Math.random() * 40 + 10;
    bubble.style.width = `${size}px`;
    bubble.style.height = `${size}px`;
    bubble.style.left = `${Math.random() * 100}%`;
    bubble.style.animationDuration = `${Math.random() * 5 + 3}s`;
    animationContainer.appendChild(bubble);
    setTimeout(() => bubble.remove(), 8000);
  }
}
setInterval(createBubbles, 2000);

// Handle form submission
const checkoutForm = document.getElementById("checkoutForm");
const successPopup = document.getElementById("successPopup");

checkoutForm.addEventListener("submit", function (event) {
  event.preventDefault(); // Prevent the default form submission

  // Display the success message popup
  successPopup.classList.add("show");

  // Hide the popup after 3 seconds
  setTimeout(function () {
    successPopup.classList.remove("show");
  }, 3000);
});
