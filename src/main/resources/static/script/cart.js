function createSparkles() {
  const sparkleContainer = document.querySelector(".background-container");
  for (let i = 0; i < 50; i++) {
    let sparkle = document.createElement("div");
    sparkle.className = "sparkle";
    sparkleContainer.appendChild(sparkle);

    let size = Math.random() * 6 + 3;
    sparkle.style.width = size + "px";
    sparkle.style.height = size + "px";
    sparkle.style.left = Math.random() * 100 + "%";
    sparkle.style.top = Math.random() * 100 + "%";
    sparkle.style.animationDuration = Math.random() * 3 + 2 + "s";
  }
}
createSparkles();
