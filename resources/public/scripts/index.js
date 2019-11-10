let input = document.getElementById('rarity-tier');
let log = document.getElementById('javascript-test');

input.oninput = handleInput;

function handleInput(e) {
  log.textContent = `The field's value is
      ${e.target.value}.`;
}
