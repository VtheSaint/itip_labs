import json
from random import randint
from datetime import datetime

# Генерируем случайные данные для 50 экземпляров Products
products = []

options = {
    "name" : [
        "Book",
        "Table",
        "Chair",
        "Bed",
        "Fridge",
        "Lamp"
    ],
    "sold_at" : [
        "28/01/2023",
        "29/01/2023",
        "30/01/2023",
        "31/01/2023",
    ],
    "manufacturer_country": [
        "Russia",
        "China",
        "Italy",
        "German",
        "France"
    ]
}

for _ in range(50):
    product = {
        "sold_at": options["sold_at"][randint(0,3)],  # Текущая дата и время
        "name": options["name"][randint(0,5)],
        "count": randint(1, 100),
        "sold_price": randint(10, 1000),
        "manufacturer_country": options["manufacturer_country"][randint(0,4)]
    }
    products.append(product)

# Записываем данные в JSON-файл
with open("InputData.json", "w") as json_file:
    body = {
        "data": products
    }
    json.dump(body, json_file)
    # json.dump(products, json_file, indent=4)

print("Файл 'InputData.json' успешно создан.")
