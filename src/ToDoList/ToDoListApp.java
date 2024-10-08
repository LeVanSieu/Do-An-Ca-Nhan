
package ToDoList;
    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
public class ToDoListApp extends JFrame {
    private ToDoList toDoList;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskInput;

    public ToDoListApp() {
        toDoList = new ToDoList();

        setTitle("To-Do List");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        taskInput = new JTextField();
        panel.add(taskInput, BorderLayout.CENTER);

        JButton addButton = new JButton("Thêm công việc");
        panel.add(addButton, BorderLayout.EAST);
        
        addButton.setBackground(Color.GREEN); 
        addButton.setForeground(Color.BLACK);

        add(panel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton completeButton = new JButton("Hoàn thành");
        JButton removeButton = new JButton("Xóa công việc");
        
        completeButton.setBackground(Color.YELLOW);  
        completeButton.setForeground(Color.BLACK);
        
        removeButton.setBackground(Color.RED);    
        removeButton.setForeground(Color.WHITE);
                
        buttonPanel.add(completeButton);
        buttonPanel.add(removeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskDescription = taskInput.getText();
                if (!taskDescription.isEmpty()) {
                    toDoList.addTask(taskDescription);
                    taskListModel.addElement("[ ] " + taskDescription);
                    taskInput.setText("");
                }
            }
        });

        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    toDoList.completeTask(selectedIndex);
                    String taskDescription = toDoList.getTasks().get(selectedIndex).getDescription();
                    taskListModel.set(selectedIndex, "[X] " + taskDescription);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    toDoList.removeTask(selectedIndex);
                    taskListModel.remove(selectedIndex);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoListApp().setVisible(true);
            }
        });
    }
}
