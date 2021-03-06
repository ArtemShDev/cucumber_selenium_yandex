
Feature: тесты поиска товаров в Яндекс.Маркете

  @Run
  Scenario Outline: тестирование поиска в yandex.ru
    Given открыть браузер
    When выполнить переход к следующей странице 'https://www.yandex.ru/'
    When перейти по ссылке с текстом 'Яндекс.Маркет'
    When перейти в каталог
    And установить категории товаров: '<categories>' '<subcategories>'
    And установить ценовой диапазон: '<priceFrom>' '<priceTo>'
    And установить бренды:
      |HP|
      |Lenovo|
    And установить количество элементов на странице: '12'
    Then проверить количество элементов в результате поиска: '12'
    And искать в каталоге элемент 0 из результатов поиска и сравнить результаты по title
    And закрыть браузер

    Examples:
      |priceFrom|priceTo|categories|subcategories|
      |10000|900000|Компьютеры|Ноутбуки|
      |100000|900000|Компьютеры|Ноутбуки|
