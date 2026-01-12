// Zestaw wybranych miejsc
const selectedSeats = new Set();

// Pobranie showId i CSRF
const showId = document.body.dataset.showId;
const csrfToken = document.querySelector('meta[name="_csrf"]').content;
const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;

// Wszystkie miejsca
const seatDivs = document.querySelectorAll('.seat');

// Dodajemy click listener dla miejsc
seatDivs.forEach(div => {
    div.addEventListener('click', () => {
        if (div.classList.contains('taken')) return; // zajęte miejsce

        const row = div.dataset.row;
        const seat = div.dataset.seat;
        const seatKey = `R${row}-S${seat}`;

        if (selectedSeats.has(seatKey)) {
            selectedSeats.delete(seatKey);
            div.classList.remove('selected');
        } else {
            selectedSeats.add(seatKey);
            div.classList.add('selected');
        }

        updateSelectedSeatsUI();
    });
});

// Aktualizacja listy wybranych miejsc
function updateSelectedSeatsUI() {
    const list = document.getElementById('selectedSeats');
    list.innerHTML = '';

    selectedSeats.forEach(seat => {
        const li = document.createElement('li');
        li.textContent = seat;
        list.appendChild(li);
    });
}

// Dodanie wybranych miejsc do koszyka
const addToCartBtn = document.getElementById('addToCartBtn');
if (addToCartBtn) {
    addToCartBtn.addEventListener('click', async () => {
        for (const seatKey of selectedSeats) {
            const [r, s] = seatKey.replace('R','').split('-S');

            try {
                const response = await fetch('/cart/add', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        [csrfHeader]: csrfToken
                    },
                    body: JSON.stringify({
                        showId: showId,
                        row: parseInt(r),
                        seat: parseInt(s)
                    })
                });

                if (!response.ok) throw new Error('Nie udało się dodać miejsca do koszyka');

            } catch (err) {
                console.error(err);
                alert(err.message);
            }
        }

        alert('Wybrane miejsca dodane do koszyka!');
    });
}
