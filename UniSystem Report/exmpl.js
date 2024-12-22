// Booking Chart
var bookingCtx = document.getElementById('bookingChart').getContext('2d');
var bookingChart = new Chart(bookingCtx, {
    type: 'doughnut',
    data: {
        datasets: [{
            data: [90, 10],
            backgroundColor: ['#89CFFB', '#EEEEEE'],
        }],
        labels: ['Completed', 'Remaining']
    },
    options: {
        cutout: '70%',
        plugins: {
            legend: {
                display: false
            }
        }
    }
});

// Revenue Chart
var revenueCtx = document.getElementById('revenueChart').getContext('2d');
var revenueChart = new Chart(revenueCtx, {
    type: 'doughnut',
    data: {
        datasets: [{
            data: [80, 20],
            backgroundColor: ['#FEDC62', '#EEEEEE'],
        }],
        labels: ['Achieved', 'Remaining']
    },
    options: {
        cutout: '70%',
        plugins: {
            legend: {
                display: false
            }
        }
    }
});

// Weekly Revenue Line Chart
var weeklyRevenueCtx = document.getElementById('weeklyRevenueChart').getContext('2d');
var weeklyRevenueChart = new Chart(weeklyRevenueCtx, {
    type: 'line',
    data: {
        labels: ['1 sem', '2 sem', '3 sem', '4 sem', '5 sem', '6 sem', '7 sem', '8 sem'],
        datasets: [
            {
                label: 'You',
                data: [3, 3.2, 2.9, 3, 3.1, 3.5, 3.3, 3.6],
                borderColor: '#89CFFB',
                fill: false
            },
            {
                label: 'Other Students',
                data: [2.3, 2.5, 2.7, 2.9, 2.7, 3, 3.1, 2.9],
                borderColor: '#FEDC62',
                fill: false
            }
        ]
    },
    options: {
        plugins: {
            legend: {
                display: false,
                position: 'top',
            }
        },
        scales: {
            x: {
                beginAtZero: true
            },
            y: {
                beginAtZero: true
            }
        }
    }
}); 

// Top Selling Tour


const tourGrid = document.querySelector('.tour-grid');

tours.forEach(tour => {
    const tourCard = document.createElement('div');
    tourCard.classList.add('tour-card');
    tourCard.innerHTML = `
        <div class="tour-image"></div>
        <h4>${tour.name}</h4>
        <div class="tour-rating">
            ${'⭐'.repeat(Math.floor(tour.rating))}
            <span>(${tour.rating})</span>
        </div>
        <div class="tour-price">${tour.price}</div>
    `;
    tourGrid.appendChild(tourCard);
});