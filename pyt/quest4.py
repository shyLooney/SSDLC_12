import os
import xml.etree.ElementTree as ET

class Quest_4():
    def create_xml_file(self):
        # Создание файла XML вручную с помощью редактора
        file_name = input("Введите имя файла: ")
        try:
            with open(file_name, 'w') as file:
                file.write("<root>\n</root>")
            print(f"Файл {file_name} создан.")
        except:
            print("Ошибка при создании файла.")
    
    
    def write_to_xml_file(self):
        # Запись новых данных в файл XML из консоли
        file_name = input("Введите имя файла: ")
        try:
            data = input("Введите данные: ")
            tree = ET.parse(file_name)
            root = tree.getroot()
    
            new_element = ET.Element("new_data")
            new_element.text = data
    
            root.append(new_element)
            tree.write(file_name)
            print("Данные успешно записаны в файл.")
        except:
            print("Ошибка при записи в файл.")
    
    
    def read_xml_file(self):
        # Чтение файла XML и вывод его содержимого в консоль
        file_name = input("Введите имя файла: ")
        try:
            tree = ET.parse(file_name)
            root = tree.getroot()
            print("Содержимое файла:")
            for element in root:
                print(element.tag, "-", element.text)
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
            print("1. Создать файл XML")
            print("2. Записать данные в файл XML")
            print("3. Прочитать файл XML")
            print("4. Удалить файл")
            print("5. Выход")
        
            choice = input("Введите номер действия: ")
        
            if choice == '1':
                self.create_xml_file()
            elif choice == '2':
                self.write_to_xml_file()
            elif choice == '3':
                self.read_xml_file()
            elif choice == '4':
                self.delete_file()
            elif choice == '5':
                break
            else:
                print("Неверный выбор. Попробуйте снова.")
q = Quest_4()
q.menu()