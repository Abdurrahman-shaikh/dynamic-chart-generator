const durObj = document.getElementById("data");
const canvas = document.getElementById("myCanvas");
const ctx = canvas.getContext("2d");

function drawPieChart(data) {
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    let total = data.reduce((sum, { Duration }) => sum + parseInt(Duration), 0);
    let currentAngle = 0;

    for (let result of data) {
        let sliceAngle = (result.Duration / total) * 2 * Math.PI;
        ctx.beginPath();
        ctx.arc(100, 100, 100, currentAngle, currentAngle + sliceAngle);
        ctx.lineTo(100, 100);
        ctx.fillStyle = getRandomColor();
        ctx.fill();

        let textAngle = currentAngle + sliceAngle / 2;
        let textX = 100 + Math.cos(textAngle) * (100 / 1.5);
        let textY = 100 + Math.sin(textAngle) * (100 / 1.5);

        ctx.fillStyle = '#000';
        ctx.font = '12px Arial';
        ctx.fillText(result.Email, textX, textY);

        currentAngle += sliceAngle;
    }
}

function drawBarChart(data) {
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    let maxDuration = Math.max(...data.map(item => item.Duration));
    let barWidth = 20;
    let spaceBetweenBars = 30;
    let startX = 50;
    let startY = 350;

    let numbersList = '';
    for (let i = 0; i < data.length; i++) {
        let barHeight = (data[i].Duration / maxDuration) * 300;
        let x = startX + i * (barWidth + spaceBetweenBars);
        let y = startY - barHeight;

        ctx.fillStyle = getRandomColor();
        ctx.fillRect(x, y, barWidth, barHeight);

        ctx.fillStyle = '#000';
        ctx.font = '10px Arial';
        let number = i + 1;
        ctx.fillText(number, x + barWidth / 2 - 3, y + barHeight / 2 + 3);

        numbersList += number + '. ' + data[i].Email + '\n';
    }

    ctx.fillStyle = '#000';
    ctx.font = '10px Arial';
    ctx.fillText(numbersList, startX, 30);
}





function fetchData(durValue) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if ((xhr.status >= 200 && xhr.status < 300) || xhr.status === 304) {
                let jsonObj = JSON.parse(xhr.responseText);
                let data = jsonObj.data;
                if (durValue === "cm" || durValue === "py") {
                    drawPieChart(data);
                } else if (durValue === "cy") {
                    drawBarChart(data);
                }
            }
        }
    };
    xhr.open("GET", `fc?type=model&page=AdminLogin&dur=${durValue}`, true);
    xhr.send();
}

durObj.addEventListener("change", () => {
    const durValue = durObj.value;
    fetchData(durValue);
});

function getRandomColor() {
    let letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}
