package com.raihanfahrifi3009.assessment1mobpro.ui.screen

import android.content.res.Configuration
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.raihanfahrifi3009.assessment1mobpro.R
import com.raihanfahrifi3009.assessment1mobpro.ui.theme.Assessment1MobproTheme
import com.raihanfahrifi3009.assessment1mobpro.util.ViewModelFactory

const val KEY_ID_BANK = "idBank"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailBankScreen(navController: NavHostController, id: Long? = null){
    val context = LocalContext.current
    val factory = ViewModelFactory(context)

    val viewModel: BankViewModel = viewModel(factory = factory)

    var namabank by remember { mutableStateOf("") }
    var catatan by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var jenisBank by remember { mutableStateOf("pemerintah") }
    var imagePath by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        if (id == null) return@LaunchedEffect
        val  data = viewModel.getDataBank(id) ?: return@LaunchedEffect
        namabank = data.namabank
        catatan = data.catatan
        jenisBank = data.jenisBank
        imagePath = data.imagePath
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    if(id == null)
                        Text(text = stringResource(id = R.string.tambah_databank))
                    else
                        Text(text = stringResource(id = R.string.edit_databank))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = {
                        if(namabank == "" || catatan == ""){
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                            return@IconButton
                        }

                        if(id == null){
                            viewModel.insert(namabank, catatan, jenisBank, imagePath)
                        } else {
                            viewModel.update(id, namabank, catatan, jenisBank, imagePath)
                        }
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if(id != null){
                        DeleteAction {
                            showDialog = true
                        }
                    }
                }
            )
        }
    ) { padding ->
        FormDataBank(
            title = namabank,
            onTitleChange = { namabank = it },
            desc = catatan,
            onDescChange = { catatan = it },
            jenisBank = jenisBank,
            onJenisBankChange = { jenisBank = it },
            imagePath = imagePath,
            onImagePicked = { imagePath = it },
            modifier = Modifier.padding(padding)
        )

        if(id != null && showDialog){
            DisplayAlertDialog(
                onDismissRequest = { showDialog = false }) {
                showDialog = false
                viewModel.delete(id)
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun FormDataBank(
    title: String, onTitleChange: (String) -> Unit,
    desc: String, onDescChange: (String) -> Unit,
    jenisBank: String, onJenisBankChange: (String) -> Unit,
    imagePath: String, onImagePicked: (String) -> Unit,
    modifier: Modifier
){
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { onTitleChange(it) },
            label = { Text(text = stringResource(R.string.nama_bank)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = desc,
            onValueChange = { onDescChange(it) },
            label = { Text(text = stringResource(R.string.isi_catatanbank)) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            ),
            modifier = Modifier.fillMaxWidth()
        )

        ImagePickerExample(imagePath = imagePath, onImagePicked = onImagePicked)

        Text(text = stringResource(R.string.jenis_bank))
        Row {
            listOf("pemerintah", "swasta").forEach { jenis ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .selectable(
                            selected = jenisBank == jenis,
                            onClick = { onJenisBankChange(jenis) }
                        )
                        .padding(end = 16.dp)
                ) {
                    RadioButton(
                        selected = jenisBank == jenis,
                        onClick = { onJenisBankChange(jenis) }
                    )
                    Text(
                        text = jenis.replaceFirstChar { it.uppercase() },
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }

    }
}

@Composable
fun DeleteAction(delete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.lainnya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(id = R.string.hapus))
                },
                onClick = {
                    expanded = false
                    delete()
                }
            )
        }
    }
}

@Composable
fun ImagePickerExample(
    imagePath: String,
    onImagePicked: (String) -> Unit
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            onImagePicked(it.toString())
        }
    }

    Button(
        onClick = { launcher.launch("image/*") },
        modifier = Modifier.semantics {
            contentDescription = context.getString(R.string.pilih_gambar)
        }
    ) {
        Text(text = stringResource(R.string.pilih_gambar))
    }

    if (imagePath.isNotEmpty()) {
        Text(
            text = stringResource(R.string.gambar_terpilih) + ": $imagePath",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.semantics {
                contentDescription = context.getString(R.string.gambar_terpilih) + ": $imagePath"
            }
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailBankScreenPreview(){
    Assessment1MobproTheme {
        DetailBankScreen(rememberNavController())
    }
}