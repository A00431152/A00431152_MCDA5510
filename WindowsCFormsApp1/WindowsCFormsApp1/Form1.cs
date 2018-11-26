using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsCFormsApp1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {


            Model2 m1 = new Model2();
            List<Customer> custs = m1.Customers.ToList();
            foreach (Customer cust in custs) {
                listView1.Items.Add(cust.FirstName);
            }

        }
    }
}
