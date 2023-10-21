import os

class Quest_2:
    def create_file(self):
        file_name = input("Введите имя файла: ")
        try:
            with open(file_name, "w"):
                print(f"Файл {file_name} создан.")
        except:
            print("Ошибка при создании файла.")

    def write_to_file(self):
        file_name = input("Введите имя файла: ")
        text = input("Введите строку для записи в файл: ")
        try:
            with open(file_name, "w") as file:
                file.write(text)
            print("Строка успешно записана в файл.")
        except:
            print("Ошибка при записи в файл.")

    def read_file(self):
        file_name = input("Введите имя файла: ")
        try:
            with open(file_name, "r") as file:
                contents = file.read()
                print("Содержимое файла:")
                print(contents)
        except:
            print("Ошибка при чтении файла.")

    def delete_file(self):
        file_name = input("Введите имя файла для удаления: ")
        try:
            os.remove(file_name)
            print("Файл успешно удален.")
        except:
            print("Ошибка при удалении файла.")

    def menu(self):
        while True:
            print("\nВыберите действие:")
            print("1. Создать файл")
            print("2. Записать в файл")
            print("3. Прочитать файл")
            print("4. Удалить файл")
            print("5. Выход")
            choice = input("Введите номер действия: ")
            if choice == "1":
                self.create_file()
            elif choice == "2":
                self.write_to_file()
            elif choice == "3":
                self.read_file()
            elif choice == "4":
                self.delete_file()
            elif choice == "5":
                break
            else:
                print("Неверный выбор. Попробуйте снова.")
q = Quest_2()
q.menu()