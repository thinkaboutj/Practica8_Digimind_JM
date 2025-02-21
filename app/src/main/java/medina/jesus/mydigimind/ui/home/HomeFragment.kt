package medina.jesus.mydigimind.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import medina.jesus.mydigimind.R
import medina.jesus.mydigimind.Recordatorio
import medina.jesus.mydigimind.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var adapter: GridViewAdapter? = null



    companion object{
        val listaRecordatorios = ArrayList<Recordatorio>()
        var first = true
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val gridView: GridView = root.findViewById(R.id.gridViewRecordatorios)

        if (first){
            filltareas()
            first = false
        }

        adapter = GridViewAdapter(requireContext(), listaRecordatorios)
        gridView.adapter = adapter

        return root
    }

    fun filltareas(){
        listaRecordatorios.add(Recordatorio("Practice 1", arrayListOf("Tuesday"), "17:30"))
        listaRecordatorios.add(Recordatorio("Practice 2", arrayListOf("Monday", "Sunday"), "17:40"))
        listaRecordatorios.add(Recordatorio("Practice 3", arrayListOf("Wednesday"), "14:00"))
        listaRecordatorios.add(Recordatorio("Practice 4", arrayListOf("Saturday"), "11:00"))
        listaRecordatorios.add(Recordatorio("Practice 5", arrayListOf("Friday"), "13:00"))
        listaRecordatorios.add(Recordatorio("Practice 6", arrayListOf("Thursday"), "10:40"))
        listaRecordatorios.add(Recordatorio("Practice 7", arrayListOf("Monday"), "12:00"))

    }

    private class GridViewAdapter() : BaseAdapter() {
        var listaTareas = ArrayList<Recordatorio>()
        var context: Context? = null

        constructor(contexto: Context, tareas: ArrayList<Recordatorio>) : this() {
            this.context = contexto
            this.listaTareas = tareas
        }


        override fun getCount(): Int {
            return listaTareas.size
        }

        override fun getItem(p0: Int): Any {
            return listaTareas[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var tarea = listaTareas[p0]
            var inflador = LayoutInflater.from(this.context)
            var vista = inflador.inflate(R.layout.recordatorio, null)

            val nombre: TextView = vista.findViewById(R.id.tvNombreRecordatorio)
            val tiempo: TextView = vista.findViewById(R.id.tvTiempoRecordatorio)
            val dias: TextView = vista.findViewById(R.id.tvDiasRecordatorio)

            nombre.setText(tarea.nombre)
            tiempo.setText(tarea.tiempo)
            dias.setText(tarea.dias.toString())

            return vista
        }
    }
}