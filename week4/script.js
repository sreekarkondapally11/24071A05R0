const apiKey = "YOUR_API_KEY"; // get from openweathermap.org
let chart;

const getWeather = async () => {
  const city = document.getElementById("city").value;

  const response = await fetch(
    `https://api.openweathermap.org/data/2.5/forecast?q=${city}&units=metric&appid=${apiKey}`
  );

  const data = await response.json();

  const temps = data.list.slice(0, 5).map(item => item.main.temp);
  const times = data.list.slice(0, 5).map(item => item.dt_txt);

  drawChart(times, temps);
};

const drawChart = (labels, data) => {
  const ctx = document.getElementById("weatherChart").getContext("2d");

  if (chart) chart.destroy();

  chart = new Chart(ctx, {
    type: "line",
    data: {
      labels: labels,
      datasets: [{
        label: "Temperature (°C)",
        data: data,
        borderWidth: 2
      }]
    }
  });
};

