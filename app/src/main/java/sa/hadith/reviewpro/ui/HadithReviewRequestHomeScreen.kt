package sa.hadith.reviewpro.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sa.hadith.reviewpro.model.ReviewPackage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HadithReviewRequestHomeScreen(
    viewModel: HadithReviewRequestViewModel,
    onSelectItem: (ReviewPackage) -> Unit,
    onQuickCheckout: () -> Unit
) {
    val catalog by viewModel.catalog.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("مراجعة الحديث المتخصصة", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("أعلى جودة في مراجعة كتب الحديث مع خ", fontSize = 12.sp, color = Color.Gray)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                // Bespoke Vector Graphic Header (>= 8 Paths branched on archetype)
                BespokeVectorIllustration(
                    archetype = "medical_portal",
                    screenIdx = 1,
                    modifier = Modifier.fillMaxWidth().height(160.dp)
                )
            }

            
            item {
                Text("المؤشرات الحيوية المباشرة (Telehealth Telemetry):", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Surface(shape = RoundedCornerShape(12.dp), color = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.weight(1f)) {
                        Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("72 bpm", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)
                            Text("النبض", fontSize = 11.sp, color = Color.Gray)
                        }
                    }
                    Surface(shape = RoundedCornerShape(12.dp), color = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.weight(1f)) {
                        Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("120/80", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text("الضغط", fontSize = 11.sp, color = Color.Gray)
                        }
                    }
                    Surface(shape = RoundedCornerShape(12.dp), color = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.weight(1f)) {
                        Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("36.8°C", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF10B981))
                            Text("الحرارة", fontSize = 11.sp, color = Color.Gray)
                        }
                    }
                }
            }

            item {
                Button(onClick = onQuickCheckout, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) {
                    Text("بدء الاستشارة الطبية المباشرة", fontWeight = FontWeight.Bold)
                }
            }
        

            item {
                Text("قائمة الخدمات المعتمدة للتخصيص:", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            items(catalog) { item ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(item.title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            Text(item.description, color = Color.Gray, fontSize = 12.sp)
                        }
                        Button(
                            onClick = { onSelectItem(item) },
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("${item.priceMinorUnits / 100} SAR")
                        }
                    }
                }
            }
        }
    }
}
