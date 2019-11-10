let input = document.getElementById('rarity-tier');
let log = document.getElementById('javascript-test');

input.oninput = handleInput;

function handleInput(e) {
  e.target.value = Math.min(e.target.value);
  input.value = e.target.value
  log.textContent = `The field's value is
      ${e.target.value || "empty"}.`;
  let container = document.getElementById("main-container");
  let children = container.getElementsByClassName('percentage');

  for (let j = children.length-1; j>=e.target.value; j--){
    container.removeChild(children[j]);
  }

  for (let i = children.length; i < Math.min(e.target.value, 100); i++) {
    let node = document.createElement("div");
    let label = document.createElement("label");
    let labelText = document.createTextNode(`Tier ${i+1}`);
    label.appendChild(labelText);
    label.classList.add("column","is-one-fifth")
    node.appendChild(label);
    let newInput = document.createElement("input");
    let inputText = `Input the probability of Tier ${i+1}`;
    newInput.placeholder = inputText;
    newInput.classList.add("column","input");
    node.appendChild(label);
    node.appendChild(newInput);
    node.classList.add("percentage")
    node.classList.add("columns")
    container.appendChild(node);
  }
}
