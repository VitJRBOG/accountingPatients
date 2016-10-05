import javax.swing.*;
import javax.swing.text.html.ObjectView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class mainClass
{
    public static void main (String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
    public static class MainFrame extends JFrame {
        public MainFrame () {
            this.setTitle("Пациенты");
            this.setBounds(400, 30, 685, 545);
            this.setMinimumSize(new Dimension(685, 545));
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            final JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());

            JPanel southPanel = new JPanel();
            southPanel.setLayout(new GridLayout(4,1));
            JPanel firstPartSecondPanel = new JPanel();
            firstPartSecondPanel.setLayout(new GridLayout(2,4));
            JPanel secondPartSPO = new JPanel();
            secondPartSPO.setLayout(new GridLayout(4,1));
            JPanel panelFilterTable = new JPanel();
            panelFilterTable.setLayout(new GridLayout(1,1));

            JLabel labelFieldName = new JLabel("ФИО");
            firstPartSecondPanel.add(labelFieldName);
            final JTextField fieldName = new JTextField();
            firstPartSecondPanel.add(fieldName);
            JLabel labelFieldDiagnosis = new JLabel("Диагноз");
            firstPartSecondPanel.add(labelFieldDiagnosis);
            final JTextField fieldDiagnosis = new JTextField();
            firstPartSecondPanel.add(fieldDiagnosis);
            JLabel labelFieldBirthday = new JLabel("Дата рождения");
            firstPartSecondPanel.add(labelFieldBirthday);
            final JTextField fieldBirthday = new JTextField();
            firstPartSecondPanel.add(fieldBirthday);
            JLabel labelFieldDocName = new JLabel("Врач");
            firstPartSecondPanel.add(labelFieldDocName);
            final JTextField fieldDocName = new JTextField();
            firstPartSecondPanel.add(fieldDocName);
            southPanel.add(firstPartSecondPanel);

            final String[] columnNames = {"ФИО", "Диагноз", "Дата рождения", "ФИО врача"};
            final String[][] dataTable = new String[25][4];
            final JTable table = new JTable(dataTable, columnNames);

            int value = 0;

            JButton sortByName = new JButton("Сортировать");
            JPanel northPanel = new JPanel();
            northPanel.setLayout(new GridLayout(1,4));
            northPanel.add(sortByName);

            mainPanel.add(northPanel, BorderLayout.NORTH);

            final JScrollPane scrollPane = new JScrollPane(table);
            mainPanel.add(scrollPane, BorderLayout.CENTER);

            class listenerAddData implements ActionListener {
                public void actionPerformed(ActionEvent event) {
                    temp[0] = fieldName.getText();
                    temp[1] = fieldDiagnosis.getText();
                    temp[2] = fieldBirthday.getText();
                    temp[3] = fieldDocName.getText();

                    for (int n = 0; n < 4; n++) {
                        if (temp[n].isEmpty()) {
                            temp[n] = "n/d";
                        }
                        dataTable[varCountRow][n] = temp[n];
                    }
                    fieldName.setText("");
                    fieldDiagnosis.setText("");
                    fieldBirthday.setText("");
                    fieldDocName.setText("");

                    varCountRow++;

                    mainPanel.remove(scrollPane);
                    mainPanel.add(scrollPane);
                }
                int varCountRow = 0;
                String[] temp = new String[4];
            }

            class listenerSortData implements ActionListener {
                public void actionPerformed(ActionEvent event) {
                    while (true) {
                        if (dataTable[count][0] == null) {
                            break;
                        }
                        else {
                            count++;
                        }
                    }
                    for (int a = 0; a < arraySortByName.length; a++) {
                        for (int i = 0; i < count; i++) {
                            symbol = "";
                            symbol += dataTable[i][0].charAt(0);
                            if (symbol.equals(arraySortByName[a]) || symbol.equals(arraySortByName[a].toUpperCase())) {
                                listName.add(dataTable[i][0]);
                                listDiagnosis.add(dataTable[i][1]);
                                listBirthday.add(dataTable[i][2]);
                                listDocName.add(dataTable[i][3]);
                                listLength++;
                            }
                        }
                    }

                    for (int i = 0; i < listLength; i++) {
                        dataTable[i][0] = listName.get(i);
                        dataTable[i][1] = listDiagnosis.get(i);
                        dataTable[i][2] = listBirthday.get(i);
                        dataTable[i][3] = listDocName.get(i);
                    }

                    mainPanel.remove(scrollPane);
                    mainPanel.add(scrollPane);
                }
                int listLength = 0;
                int count = 0;
                int varForBreak;
                String symbol;
                ArrayList<String> listName = new ArrayList<String>();
                ArrayList<String> listDiagnosis = new ArrayList<String>();
                ArrayList<String> listBirthday = new ArrayList<String>();
                ArrayList<String> listDocName = new ArrayList<String>();
                String[] arraySortByName = {"n/d", "а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й", "к", "л",
                        "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ъ", "ы", "ь", "э", "ю",
                        "я",};
            }

            listenerSortData sortData = new listenerSortData();
            sortByName.addActionListener(sortData);

            final Object[][] filterDataTable = new Object[10][4];
            JTable filterTable = new JTable(filterDataTable, columnNames);
            final JScrollPane scrollPane1FilterTable = new JScrollPane(filterTable);

            listenerAddData addData = new listenerAddData();

            class listenerRemoveData implements ActionListener {
                public void actionPerformed(ActionEvent event) {

                    while (true) {
                        if (dataTable[count][0] == null) {
                            break;
                        }
                        else {
                            count++;
                        }
                    }

                    for (int i = 0; i < count; i++) {
                        if (table.isRowSelected(i)) {
                            for (int n = 0; n < 4; n++) {
                                dataTable[i][n] = null;
                            }
                        }
                    }

                    count = 0;

                    while (true) {
                        if (dataTable[count][0] == null) {
                            break;
                        }
                        else {
                            count++;
                        }
                    }

                    for (int i = 0; i < count; i++) {
                        if (dataTable[i][0] == null && dataTable[i][1] == null && dataTable[i][2] == null &&
                                dataTable[i][3] == null) {
                            for (int n = 0; n < 4; n++) {
                                dataTable[i][n] = dataTable[i++][n];
                            }
                        }
                    }

                    mainPanel.revalidate();
                }
                int count = 0;
            }

            listenerRemoveData removeData = new listenerRemoveData();

            class listenerSearchData implements ActionListener {
                public void actionPerformed(ActionEvent event) {

                    temp[0] = fieldName.getText();
                    temp[1] = fieldDiagnosis.getText();
                    temp[2] = fieldBirthday.getText();
                    temp[3] = fieldDocName.getUIClassID();

                    for (int i = 0; i < value; i++) {
                        for (int n = 0; n < value; n++) {
                            if (dataTable[i][n].equals(temp[n])) {
                                filterDataTable[i][n] = dataTable[i][n];
                            }
                            else {
                                if (dataTable[i][n].equals("")) {
                                    filterDataTable[i][n] = dataTable[i][n];
                                }
                            }
                        }
                    }

                filterTableFrame.add(scrollPane1FilterTable);
                    filterTableFrame.setBounds(400, 30, 500, 500);
                    filterTableFrame.setResizable(false);
                    filterTableFrame.setVisible(true);

                }

                JFrame filterTableFrame = new JFrame();

                String[] temp = new String[4];
            }

            listenerSearchData searchData = new listenerSearchData();

            class listenerShowAll implements ActionListener {
                public void actionPerformed(ActionEvent event)
                {
                    mainPanel.add(scrollPane, BorderLayout.CENTER);
                }
            }

            listenerShowAll showAll = new listenerShowAll();

            JButton buttonSearch = new JButton("Поиск");
            buttonSearch.addActionListener(searchData);

            JButton buttonRemove = new JButton("Удалить выделенные");
            buttonRemove.addActionListener(removeData);

            JButton buttonAdd = new JButton("Добавить в таблицу");
            buttonAdd.addActionListener(addData);

            JButton buttonShowAll = new JButton("Показать все записи");
            buttonShowAll.addActionListener(showAll);

            secondPartSPO.add(buttonSearch);
            secondPartSPO.add(buttonRemove);
            secondPartSPO.add(buttonAdd);
            secondPartSPO.add(buttonShowAll);

            mainPanel.add(secondPartSPO, BorderLayout.EAST);

            mainPanel.add(southPanel, BorderLayout.SOUTH);

            this.add(mainPanel);
        }
    }
}
