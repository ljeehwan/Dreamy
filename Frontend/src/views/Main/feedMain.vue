<!--이거 temp폴더인가-->
<template>
    <v-card class="mx-auto my-12" max-width="320">
        <v-img height="180" src="https://cdn.vuetifyjs.com/images/cards/cooking.png"></v-img>

        <v-card-title>지허니 100대 때리기</v-card-title>

        <v-card-actions>
            <v-btn color="deep-purple lighten-2" text="text">
                서와닝
            </v-btn>
            <v-spacer/>

            <v-icon left="left">
                mdi-label
            </v-icon>
            운동
        </v-card-actions>

        <v-divider class="mx-4"></v-divider>
        <v-card-actions>
            <v-btn color="deep-purple lighten-2" text="text" @click="detail">
                자세히보기
            </v-btn>
            <v-spacer/>
            <v-btn
                small="small"
                class="ma-2"
                text="text"
                icon="icon"
                color="purple lighten-2">
                <v-icon>mdi-heart</v-icon>
            </v-btn>
            <span>0</span>
            <v-btn
                small="small"
                class="ma-2"
                text="text"
                icon="icon"
                color="purple lighten-2">
                <v-icon>mdi-comment-outline</v-icon>
            </v-btn>
            <span>0</span>
            <v-btn
                small="small"
                class="ma-2"
                text="text"
                icon="icon"
                color="purple lighten-2">
                <v-icon>mdi-bookmark</v-icon>
            </v-btn>
            <span>0</span>
        </v-card-actions>
        <v-dialog v-model="loading" max-width="1100">
            <v-container>
                <v-layout row="row">
                    <v-flex md5="md5">
                        <v-card>
                            <v-img height="600" src="https://cdn.vuetifyjs.com/images/cards/cooking.png"></v-img>

                        </v-card>
                    </v-flex>
                    <v-flex md7="md7">
                        <v-card height="600">
                            <v-card-actions id="detail_top" class="detail_view">
                                <v-icon>mdi-account</v-icon>
                                서와닝

                                <v-btn
                                    class="ma-2"
                                    color="primary"
                                    rounded="rounded"
                                    small="small"
                                    @click="follow">팔로우</v-btn>
                                <v-spacer/>
                                <v-icon>mdi-human-handsup</v-icon>
                                챌린지
                            </v-card-actions>

                            <v-card-title class="detail_view" padding="5px">지허니 100대 때리기<v-spacer/>
                                <v-card-subtitle align="right">2021-01-26</v-card-subtitle>
                            </v-card-title>
                            <div align="left">
                                <v-card-text class="detail_view">자동줄바꿈이 되나안되나 음...안된당...ㅜㅀㅇㅀㅇㅀㄴㅇ루ㅘㅓ
                                </v-card-text>
                            </div>
                            <v-card-subtitle align="left">시작날짜 : 2021-01-26
                                <br>종료날짜 : 2021-07-26</v-card-subtitle>
                                <v-card-actions class="detail_view">

                                    <v-btn
                                        small="small"
                                        class="ma-2"
                                        text="text"
                                        icon="icon"
                                        color="purple lighten-2">
                                        <v-icon>mdi-heart-outline</v-icon>
                                    </v-btn>
                                    <span>0</span>
                                    <v-btn
                                        small="small"
                                        class="ma-2"
                                        text="text"
                                        icon="icon"
                                        color="purple lighten-2">
                                        <v-icon>mdi-bookmark-outline</v-icon>
                                    </v-btn>
                                    <span>0</span>
                                    <v-spacer/>
                                    <v-btn color="green">참가하기</v-btn>
                                </v-card-actions>

                                <v-divider class="mx-4"></v-divider>
                                
                            </v-card>
                            
                        </v-flex>
                    </v-layout>
                </v-container>
            </v-dialog>
        </v-card>
</template>

<script>
        export default {
            data: () => ({loading: false, selection: 1}),

            methods: {
                detail() {
                    this.loading = true

                }
            }
        }
</script>

<style>
        .data {
            text-align: left;
        }

        .detail_view {
            padding: 0 15px;
        }
        #detail_top {
            padding-top: 15px;
        }
</style>