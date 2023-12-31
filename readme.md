### Сортировка записей таблицы

Этот метод сортирует переданный список записей таблицы в соответствии с указанной колонкой, соблюдая следующие правила:

1. **Обработка null и пустых строк**: Значения null и пустые строки в колонке сортируются первыми, а затем идут непустые значения.

2. **Разделение строк**: Каждая строка разбивается на подстроки следующим образом:
    - Выделяются непрерывные максимальные фрагменты строки, состоящие только из цифр.
    - Все остальные фрагменты считаются нечисловыми подстроками.

3. **Сравнение подстрок**: При сравнении двух подстрок:
    - Если обе подстроки состоят только из цифр, они интерпретируются как целые числа, и меньшее число идет первым.
    - Если хотя бы одна подстрока содержит нецифровые символы, сравнение выполняется как строки.

4. **Стабильная сортировка**: Сортировка устойчива и сохраняет исходный порядок неразличимых строк.

**Параметры:**
- `rows`: Список записей таблицы, который нужно отсортировать, например, результат SQL-запроса.
- `columnIndex`: Индекс колонки, по которой нужно провести сортировку.
