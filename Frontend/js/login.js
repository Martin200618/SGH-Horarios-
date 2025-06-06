// Asegúrate de que la URL base coincida con la de tu backend
const API_URL = "http://localhost:8085/users/";

// Escucha el evento 'submit' del formulario de login
document.getElementById("loginForm").addEventListener("submit", async function(event) {
  event.preventDefault();

  // Obtén los valores de los inputs
  const userName = document.getElementById("userName").value;
  const password = document.getElementById("password").value;

  try {
    // Realiza la petición POST al endpoint de login usando parámetros en la URL
    const response = await fetch(`${API_URL}login?userName=${encodeURIComponent(userName)}&password=${encodeURIComponent(password)}`, {
      method: "POST"
    });

    // Convierte la respuesta a texto 
    const data = await response.text();

    // Selecciona el elemento para mostrar el mensaje
    const mensajeElement = document.getElementById("mensaje");

    if (response.ok) {
      // Éxito: muestra el mensaje en verde
      mensajeElement.style.color = "green";
      mensajeElement.textContent = data;
      // Opcional: redirigir a otra página en caso de login exitoso
      // window.location.href = "home.html";
    } else {
      // Error: muestra el mensaje en rojo
      mensajeElement.style.color = "#d32f2f";
      mensajeElement.textContent = data;
    }
  } catch (error) {
    // Si ocurre un error en la solicitud, muestra el error en la consola y en el mensaje
    console.error("Error en la solicitud de login:", error);
    const mensajeElement = document.getElementById("mensaje");
    mensajeElement.style.color = "#d32f2f";
    mensajeElement.textContent = "Error en la solicitud.";
  }
});