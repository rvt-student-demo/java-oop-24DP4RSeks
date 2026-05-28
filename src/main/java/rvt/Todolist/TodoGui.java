package rvt.Todolist;

import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class TodoGui extends JFrame {
    private final TodoList todos = new TodoList();
    private final DefaultListModel<String> model = new DefaultListModel<>();

    public TodoGui() {
        JTextField input = new JTextField();
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JList<String> list = new JList<>(model);
        JPanel top = new JPanel(new BorderLayout());

        top.add(input, BorderLayout.CENTER);
        top.add(addButton, BorderLayout.EAST);
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);
        add(deleteButton, BorderLayout.SOUTH);

        addButton.addActionListener(event -> {
            todos.add(input.getText());
            input.setText("");
            refresh();
        });
        input.addActionListener(addButton.getActionListeners()[0]);
        deleteButton.addActionListener(event -> {
            int selected = list.getSelectedIndex();
            if (selected >= 0) {
                todos.remove(selected + 1);
                refresh();
            }
        });

        setTitle("Todo List");
        setSize(350, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        refresh();
    }

    private void refresh() {
        model.clear();
        todos.getTasksWithIds().forEach(model::addElement);
    }
}
