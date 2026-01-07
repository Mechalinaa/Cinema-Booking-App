const selectedSeats = new Set();

function toggleSeat(div) {
    const row = div.dataset.row;
    const seat = div.dataset.seat;
    const seatId = `R${row}-S${seat}`; // unikalny identyfikator

    if (selectedSeats.has(seatId)) {
        selectedSeats.delete(seatId);
        div.classList.remove('selected');
    } else {
        selectedSeats.add(seatId);
        div.classList.add('selected');
    }
    updateSelectedSeats();
}

function updateSelectedSeats() {
    const list = document.getElementById('selectedSeats');
    list.innerHTML = '';
    selectedSeats.forEach(seat => {
        const li = document.createElement('li');
        li.textContent = seat;
        list.appendChild(li);
    });
}
