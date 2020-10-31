
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import acm.gui.TableLayout;

public class SliderBox extends JPanel{

	JLabel name_lab;
	JLabel min_val_lab;
	JLabel max_val_lab;
	JLabel def_val_lab;
	JSlider slider;

	// constructor
	/**
	 * This constructor creates an instance of a JPanel using the default
	 * layout manager, and populates it as follows:
	 *  Parameter: JLabel --- JSlider --- JLabel JLabel
	 * @param name String, name of slider
	 * @param min_val integer, min slider value
	 * @param max_val integer, max slider value
	 * @param def_val integer, default slider value
	 */
	public SliderBox(String name, int min_val, int max_val, int def_val)
	{

		this.setLayout(new TableLayout(1, 5));

		name_lab  = new JLabel(name);
		this.add(name_lab,"width = 100");

		min_val_lab = new JLabel(min_val+"");
		this.add(min_val_lab,"width = 25");

		slider = new JSlider(min_val, max_val, def_val);
		this.add(slider, "width = 100");

		max_val_lab = new JLabel(max_val+"");
		this.add(max_val_lab,"width = 100");

		def_val_lab = new JLabel(def_val+"");
		def_val_lab.setForeground(Color.blue);
		this.add(def_val_lab,"width = 80");

	}

	public SliderBox(String name, double min_val, double max_val, double def_val)
	{

		this.setLayout(new TableLayout(1, 5));

		name_lab  = new JLabel(name);
		this.add(name_lab,"width = 100");

		min_val_lab = new JLabel(min_val+"");
		this.add(min_val_lab,"width = 25");

		slider = new JSlider((int)(min_val*10), (int)(max_val*10), (int)(def_val*10));
		this.add(slider, "width = 100");

		max_val_lab = new JLabel(max_val+"");
		this.add(max_val_lab,"width = 100");

		def_val_lab = new JLabel(def_val+"");
		def_val_lab.setForeground(Color.blue);
		this.add(def_val_lab,"width = 80");

	}


}

