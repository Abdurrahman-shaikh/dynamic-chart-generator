function drawFlowChart() {
    const durObj = document.getElementById("data");
    durObj.addEventListener("change", () => {
        const durValue = durObj.value;
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if ((xhr.status >= 200 && xhr.status < 300) || xhr.status === 304) {
                    let jsonObj = JSON.parse(xhr.responseText);
                    let data = jsonObj.data;

                    let canvas = document.querySelector("#flowChart");
                    let ctx = canvas.getContext("2d");

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
            }
        };

        function getRandomColor() {
            let letters = '0123456789ABCDEF';
            let color = '#';
            for (let i = 0; i < 6; i++) {
                color += letters[Math.floor(Math.random() * 16)];
            }
            return color;
        }

        xhr.open("get", `fc?type=model&page=AdminLogin&dur=${durValue}`, true);
        xhr.send(null);
    });
}
