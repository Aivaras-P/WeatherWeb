document.addEventListener('DOMContentLoaded', () => {
  const searchBtn = document.getElementById('searchBtn');
  const cityInput = document.getElementById('cityInput');
  const cityNameElem = document.getElementById('city-name');
  const weatherIconElem = document.getElementById('weather-icon');
  const weatherDescElem = document.getElementById('weather-description');
  const feelsLikeContainer = document.getElementById("feels-like-container");
  const tempValueElem = document.querySelector('.temperature .speed-value');
  const rainChanceElem = document.querySelector('.rain-chance .chance-value');
  const windSpeedElem = document.querySelector('.wind-speed .speed-value');
  const moonPhaseElem = document.querySelector('.moon-phase .moon-value');
  const sunriseElem = document.querySelector('.sunrise .sun-value');
  const sunsetElem = document.querySelector('.sunset .sunset-value');
  const airQualityElem = document.querySelector('.air-quality .quality-value');
  const humidityElem = document.querySelector('.humidity .humidity-value');

  let forecastData = null;
  const today = new Date();

  const dayButtons = [
    document.getElementById('mondayBtn'),
    document.getElementById('tuesdayBtn'),
    document.getElementById('wednesdayBtn'),
    document.getElementById('thursdayBtn'),
    document.getElementById('fridayBtn'),
    document.getElementById('saturdayBtn'),
    document.getElementById('sundayBtn')
  ];

  const weekdaysShort = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];

  function pad(num) {
    return num.toString().padStart(2, '0');
  }

  // Pakeičiam mygtukų tekstą pagal šiandieną
  dayButtons.forEach((btn, idx) => {
    const currentDay = new Date(today);
    currentDay.setDate(today.getDate() + idx);
    const month = pad(currentDay.getMonth() + 1);
    const day = pad(currentDay.getDate());
    const dayName = weekdaysShort[currentDay.getDay()];
    btn.textContent = `${dayName} ${month}/${day}`;

    btn.addEventListener('click', () => {
      updateWeather(idx);

      const selectedDate = new Date(today);
      selectedDate.setDate(today.getDate() + idx);
      const m = pad(selectedDate.getMonth() + 1);
      const d = pad(selectedDate.getDate());
      const dateString = `${m}-${d}`;
      const city = citySearch.value.trim() || 'Vilnius';
      history.pushState(null, '', `/${city}/${dateString}`);
    });
  });

  window.addEventListener('load', () => {
    const pathParts = window.location.pathname.split('/').filter(Boolean);
    if (pathParts.length >= 1) {
      const cityFromURL = pathParts[0];
      cityInput.value = cityFromURL;

      fetch(`http://localhost:8085/api/weather/${cityFromURL}`)
          .then(res => {
            if (!res.ok) throw new Error('City not found');
            return res.json();
          })
          .then(data => {
            forecastData = data;
            updateWeather(0); // 0 = šiandien
          })
          .catch(err => {
            alert(err.message);
          });
    }
  });

  const themeToggle = document.getElementById('theme-toggle');
  themeToggle.addEventListener("click", () => {
    document.body.classList.toggle("dark-mode");
    const icon = themeToggle.querySelector("i");
    icon.classList.toggle("fa-moon");
    icon.classList.toggle("fa-sun");
  });

  function getAirQualityLabel(aqi) {
    switch (aqi) {
      case 1: return "Good";
      case 2: return "Moderate";
      case 3: return "Unhealthy for sensitive groups";
      case 4: return "Unhealthy";
      case 5: return "Very Unhealthy";
      case 6: return "Hazardous";
      default: return `${aqi} Unknown`;
    }
  }

  const weatherImages = {
    "clear sky": "sun.png",
    "few clouds": "cloud.png",
    "scattered clouds": "cloud.png",
    "broken clouds": "cloudy.png",
    "shower rain": "rain.png",
    "rain": "rain.png",
    "thunderstorm": "thunderstorm.png",
    "snow": "snow.png",
    "mist": "mist.png",
    "moderate rain": "rain.png",
    "light rain": "rain.png"
  };

  function updateWeather(dayIndex = 0) {
    if (!forecastData) return;

    const day = forecastData.forecast.daily[dayIndex];
    const weather = day.weather[0].description.toLowerCase();
    const temp = day.temp.day;
    const windSpeed = day.wind_speed;
    const humidity = day.humidity;
    const aqi = forecastData.air.currentConditions?.aqi ?? 'N/A';
    const airQualityLabel = typeof aqi === "number" ? getAirQualityLabel(aqi) : aqi;

    // Moon phase
    let moonPhase = "-";
    let sunrise = "-";
    let sunset = "-";

    if (forecastData.moon?.days && forecastData.moon.days[dayIndex]) {
      const dayData = forecastData.moon.days[dayIndex];
      const phaseValue = dayData.moonphase;
      moonPhase = getMoonPhaseDescription(phaseValue);
      sunrise = forecastData.moon.days[dayIndex].sunrise || "-";
      sunset = forecastData.moon.days[dayIndex].sunset || "-";

    }
    document.querySelector('.sunrise .sun-value').textContent = sunrise;
    document.querySelector('.sunset .sunset-value').textContent = sunset;

    function getMoonPhaseDescription(value) {
      if (value === 0 || value === 1) return "New Moon";
      if (value > 0 && value < 0.25) return "Waxing Crescent";
      if (value === 0.25) return "First Quarter";
      if (value > 0.25 && value < 0.5) return "Waxing Gibbous";
      if (value === 0.5) return "Full Moon";
      if (value > 0.5 && value < 0.75) return "Waning Gibbous";
      if (value === 0.75) return "Last Quarter";
      if (value > 0.75 && value < 1) return "Waning Crescent";
      return "Unknown";


    }


    // UI update
    weatherDescElem.textContent = weather;
    tempValueElem.textContent = temp.toFixed(1);
    windSpeedElem.innerHTML = `<i class="fas fa-wind wind-icon"></i> ${windSpeed} km/h`;
    airQualityElem.textContent = airQualityLabel;
    humidityElem.innerHTML = `<i class="fas fa-burn"></i> ${humidity} %`;
    weatherIconElem.src = `/${weatherImages[weather] || 'sun.png'}`;
    weatherIconElem.alt = weather;
    moonPhaseElem.textContent = moonPhase;


    if (dayIndex === 0 && forecastData.hourly?.hourly) {
      renderHourlyChart(forecastData.hourly.hourly);
    } else {
      if (hourlyChartInstance) {
        hourlyChartInstance.destroy();
        hourlyChartInstance = null;
      }
    }
    if (dayIndex === 0 && forecastData.current?.main?.feels_like !== undefined) {
      const feelsLike = forecastData.current.main.feels_like;
      document.getElementById("feels-like").textContent = feelsLike.toFixed(1);
      feelsLikeContainer.style.display = "block";

      document.getElementById("night-temp-container").style.display = "none";

    } else if (dayIndex !== 0 && day.temp) {

      document.getElementById("night-temp").innerHTML = `${day.temp.night.toFixed(1)} &deg;C`;
      document.getElementById("night-temp-container").style.display = "block";
      feelsLikeContainer.style.display = "none";

    } else {

      feelsLikeContainer.style.display = "none";
      document.getElementById("night-temp-container").style.display = "none";
    }


  }


  let hourlyChartInstance = null;

  function renderHourlyChart(hourlyData) {
    const labels = hourlyData.slice(0, 12).map(hour => {
      const date = new Date(hour.dt * 1000);
      const timeLabel = date.getHours() + ":00";


      const windSpeed = hour.wind_speed || 0;


      return `${timeLabel} (${windSpeed} km/h)`;
    });

    const temps = hourlyData.slice(0, 12).map(hour => hour.temp);

    const ctx = document.getElementById('hourlyChart').getContext('2d');

    if (hourlyChartInstance) {
      hourlyChartInstance.destroy();
    }

    hourlyChartInstance = new Chart(ctx, {
      type: 'line',
      data: {
        labels: labels,
        datasets: [{
          label: 'Temperature (°C)',
          data: temps,
          borderColor: '#007BFF',
          backgroundColor: 'rgba(0, 123, 255, 0.1)',
          fill: true,
          tension: 0.3,
          pointRadius: 3
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            display: true
          }
        },
        scales: {
          y: {
            beginAtZero: false
          }
        }
      }
    });
  }

  const citySearch = document.getElementById('citySearch');
  const suggestionsBox = document.getElementById('suggestions');

  citySearch.addEventListener('input', async () => {
    const query = citySearch.value.trim();
    if (query.length < 2) {
      suggestionsBox.style.display = 'none';
      suggestionsBox.innerHTML = '';
      return;
    }

    try {
      const response = await fetch(`/api/weather/cities?query=${encodeURIComponent(query)}`);
      if (!response.ok) throw new Error('Error getting the cities');

      const cities = await response.json();

      if (cities.length === 0) {
        suggestionsBox.style.display = 'none';
        suggestionsBox.innerHTML = '';
        return;
      }

      suggestionsBox.innerHTML = '';
      cities.forEach(city => {
        const div = document.createElement('div');
        div.textContent = `${city.name}${city.state ? ', ' + city.state : ''}, ${city.country}`;
        div.classList.add('suggestion-item');
        div.addEventListener('click', () => {
          citySearch.value = city.name;
          suggestionsBox.style.display = 'none';


          if (typeof fetchWeather === 'function') {
            fetchWeather(city.name);
          }
        });
        suggestionsBox.appendChild(div);
      });

      suggestionsBox.style.display = 'block';
    } catch (error) {
      console.error(error);
    }
  });



  searchBtn.addEventListener('click', () => {
    const city = citySearch.value.trim();
    if (!city) {
      alert('Please enter a city name.');
      return;
    }
    cityNameElem.textContent = city;

    fetch(`http://localhost:8085/api/weather/${city}`)
        .then(res => {
          if (!res.ok) throw new Error('City not found');
          return res.json();
        })
        .then(data => {
          forecastData = data;
          window.forecastData = data;
          updateWeather(0);

          const m = pad(today.getMonth() + 1);
          const d = pad(today.getDate());
          const dateString = `${m}-${d}`;
          history.pushState(null, '', `/${city}/${dateString}`);
        })
        .catch(err => {
          alert(err.message);
        });
  });
});
