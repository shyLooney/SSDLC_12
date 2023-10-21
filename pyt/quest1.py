import psutil

class Quest_1():
    def get_disk_information(self):
        partitions = psutil.disk_partitions(self)
        
        for partition in partitions:
            print("Диск: ", partition.device)
            print("Тип файловой системы: ", partition.fstype)
            
            try:
                disk_usage = psutil.disk_usage(partition.mountpoint)
            except PermissionError:
                continue
                
            print("Метка тома: ", partition.mountpoint)
            print("Общий размер: ", self.convert_bytes(disk_usage.total))
            print("Использовано: ", self.convert_bytes(disk_usage.used))
            print("Свободно: ", self.convert_bytes(disk_usage.free))
            print("Процент использования: ", disk_usage.percent, "%")
            print("--------------------------------------")
    
    
    def convert_bytes(self, size, precision=2):
        # Функция для преобразования размера в байтах в читаемый формат (КБ, МБ, ГБ и т.д.)
        suffixes = ['B', 'KB', 'MB', 'GB', 'TB']
        index = 0
        
        while size >= 1024 and index < len(suffixes) - 1:
            size /= 1024
            index += 1
        
        return f"{round(size, precision)} {suffixes[index]}"
q = Quest_1()
q.get_disk_information()