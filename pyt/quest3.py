import json
import os

class Quest_3():
    def create_file(self):
        # Создание файла в формате JSON вручную
        file_name = input("Введите имя файла: ")
        try:
            with open(file_name, 'w') as file:
                file.write("{}")
            print(f"Файл {file_name} создан.")
        except:
            print("Ошибка при создании файла.")
    
    def create_and_write_json(self):
        # Создание нового объекта, сериализация в JSON и запись в файл
        file_name = input("Введите имя файла: ")
        data = {}
        data["key"] = input("Введите значение: ")
        data["key2"] = input("Введите значение: ")
        try:
            with open(file_name, 'w') as file:
                json.dump(data, file)
            print("Объект успешно сериализован и записан в файл.")
        except:
            print("Ошибка при записи в файл.")
    
    def read_file(self):
        # Чтение файла в консоль
        file_name = input("Введите имя файла: ")
        try:
            with open(file_name, 'r') as file:
                contents = file.read()
                print("Содержимое файла:")
                print(contents)
        except:
            print("Ошибка при чтении файла.")
    
    def delete_file(self):
        # Удаление файла
        file_name = input("Введите имя файла для удаления: ")
        try:
            os.remove(file_name)
            print("Файл успешно удален.")
        except:
            print("Ошибка при удалении файла.")
    def menu(self):
		# Главное меню
        while True:
            print("\nВыберите действие:")
            print("1. Создать файл JSON")
            print("2. Создать и записать JSON")
            print("3. Прочитать файл")
            print("4. Удалить файл")
            print("5. Выход")
            choice = input("Введите номер действия: ")
            if choice == '1':
                self.create_file()
            elif choice == '2':
                self.create_and_write_json()
            elif choice == '3':
                self.read_file()
            elif choice == '4':
                self.delete_file()
            elif choice == '5':
                break
            else:
                print("Неверный выбор. Попробуйте снова.")
q = Quest_3()
q.menu()