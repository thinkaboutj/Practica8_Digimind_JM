package medina.jesus.mydigimind.ui.dashboard

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import medina.jesus.mydigimind.R
import medina.jesus.mydigimind.Recordatorio
import medina.jesus.mydigimind.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.Calendar


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(owner = this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container,false)

        val btn_time: Button = root.findViewById(R.id.btn_time)

        btn_time.setOnClickListener{
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{ timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                btn_time.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), true).show()

        }

        val btn_save = root.findViewById(R.id.btn_save) as Button
        val et_titulo = root.findViewById(R.id.etTitulo) as EditText
        val checkMonday = root.findViewById(R.id.checkboxMonday) as CheckBox
        val checkTuesday = root.findViewById(R.id.checkboxTuesday) as CheckBox
        val checkWednesday = root.findViewById(R.id.checkboxWednesday) as CheckBox
        val checkThursday = root.findViewById(R.id.checkboxThursday) as CheckBox
        val checkFriday = root.findViewById(R.id.checkboxFriday) as CheckBox
        val checkSaturday = root.findViewById(R.id.checkboxSaturday) as CheckBox
        val checkSunday = root.findViewById(R.id.checkboxSunday) as CheckBox

        btn_save.setOnClickListener{

            var titulo = et_titulo.text.toString()
            var tiempo = btn_time.text.toString()
            var dias = ArrayList<String>()

            if(checkMonday.isChecked)
                dias.add("Monday")
            if(checkTuesday.isChecked)
                dias.add("Tuesday")
            if(checkWednesday.isChecked)
                dias.add("Wednesday")
            if(checkThursday.isChecked)
                dias.add("Thursday")
            if(checkFriday.isChecked)
                dias.add("Friday")
            if(checkSaturday.isChecked)
                dias.add("Saturday")
            if(checkSunday.isChecked)
                dias.add("Sunday")

            var tarea = Recordatorio(titulo,dias,tiempo)

            HomeFragment.listaRecordatorios.add(tarea)
            Toast.makeText(root.context, "Nueva tarea añadida",Toast.LENGTH_SHORT).show()
        }

        return root
    }
}