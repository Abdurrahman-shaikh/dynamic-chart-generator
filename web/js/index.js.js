let myChart;

document.getElementById('slotCount').addEventListener('change', function() {
    const slotCount = parseInt(this.value);
    const dataInputs = document.getElementById('dataInputs');
    dataInputs.innerHTML = '';

    for (let i = 0; i < slotCount; i++) {
        const slotIndex = i + 1;
        const inputGroup = document.createElement('div');
        inputGroup.classList.add('mb-3');

        const nameLabel = document.createElement('label');
        nameLabel.textContent = `Name of Slot ${slotIndex}:`;
        const nameInput = document.createElement('input');
        nameInput.type = 'text';
        nameInput.classList.add('form-control');
        nameInput.placeholder = `Slot ${slotIndex} Name`;
        nameInput.id = `slotName${slotIndex}`;

        const dataLabel = document.createElement('label');
        dataLabel.textContent = `Data for Slot ${slotIndex}:`;
        const dataInput = document.createElement('input');
        dataInput.type = 'number';
        dataInput.classList.add('form-control');
        dataInput.placeholder = `Data for Slot ${slotIndex}`;
        dataInput.id = `slotData${slotIndex}`;

        const colorLabel = document.createElement('label');
        colorLabel.textContent = `Color for Slot ${slotIndex}:`;
        const colorInput = document.createElement('input');
        colorInput.type = 'color';
        colorInput.classList.add('form-control');
        colorInput.value = '#000000';
        colorInput.id = `slotColor${slotIndex}`;
        inputGroup.appendChild(nameLabel);
        inputGroup.appendChild(nameInput);
        inputGroup.appendChild(dataLabel);
        inputGroup.appendChild(dataInput);
        inputGroup.appendChild(colorLabel);
        inputGroup.appendChild(colorInput);

        dataInputs.appendChild(inputGroup);
    }
});

document.getElementById('generateChartBtn').addEventListener('click', function() {
    const slotCount = parseInt(document.getElementById('slotCount').value);
    const slotNames = [];
    const slotData = [];
    const slotColors = [];

    for (let i = 0; i < slotCount; i++) {
        const name = document.getElementById(`slotName${i + 1}`).value;
        const data = parseInt(document.getElementById(`slotData${i + 1}`).value);
        const color = document.getElementById(`slotColor${i + 1}`).value;

        slotNames.push(name);
        slotData.push(data);
        slotColors.push(color);
    }

    const chartType = document.getElementById('chartType').value;

    const ctx = document.getElementById('myChart').getContext('2d');

    if (myChart) {
        myChart.destroy();
    }

    myChart = new Chart(ctx, {
        type: chartType,
        data: {
            labels: slotNames,
            datasets: [{
                label: 'Data',
                data: slotData,
                backgroundColor: slotColors,
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
});

document.getElementById('downloadModelBtn').addEventListener('click', function() {
    const imageLink = document.createElement('a');
    const canvas = document.getElementById('myChart');
    imageLink.download = 'chart.png';
    imageLink.href = canvas.toDataURL('image/png');
    imageLink.click();
});

