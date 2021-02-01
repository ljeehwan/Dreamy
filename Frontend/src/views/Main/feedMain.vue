<template>
<v-layout row wrap>
    <v-card class="mx-auto my-12" max-width="320" v-for="(list,index) in lists" :key="index">
        <v-img height="180" v-bind:src="list.imglink"></v-img>

        <v-card-title>{{list.title}}</v-card-title>

        <v-card-actions>
            <v-btn color="black" text="text">
                {{list.name}}
            </v-btn>
            <v-spacer/>

            <v-icon left="left">
                mdi-label
            </v-icon>
            {{list.category}}
        </v-card-actions>

        <v-divider class="mx-4"></v-divider>
        <v-card-actions>
            <v-btn color="black" text="text" @click="detail">
                자세히보기
            </v-btn>
            <v-spacer/>
            <v-btn
                small="small"
                class="ma-2"
                text="text"
                icon="icon">
                <v-icon>mdi-heart</v-icon>
            </v-btn>
            <span>{{list.likes}}</span>
            <v-btn
                small="small"
                class="ma-2"
                text="text"
                icon="icon">
                <v-icon>mdi-comment-outline</v-icon>
            </v-btn>
            <span>{{list.comment}}</span>
            <v-btn
                small="small"
                class="ma-2"
                text="text"
                icon="icon">
                <v-icon>mdi-bookmark</v-icon>
            </v-btn>
            <span>{{list.scraps}}</span>
        </v-card-actions>
        <v-dialog v-model="loading" max-width="1100">
            <v-container>
                <v-layout row="row">
                    <v-flex md5="md5">
                        <v-card>
                            <v-img height="650" src="https://cdn.vuetifyjs.com/images/cards/cooking.png"></v-img>

                        </v-card>
                    </v-flex>
                    <v-flex md7="md7">
                        <v-card height="650">
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
                                <v-card-text class="detail_view">자동줄바꿈이 되나안되나 음...안된당...ㅜㅀㅇㅀㅇㅀㄴㅇ루ㅘ
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

                                <v-form>

                                    <v-container>
                                        <!--댓글-->
                                        <v-card max-width="600" class="mx-auto">
                                            <v-list one-line="one-line">
                                                <template v-for="(item, index) in items">
                                                    <v-subheader v-if="item.header" :key="item.header" v-text="item.header"></v-subheader>

                                                    <v-divider v-else-if="item.divider" :key="index" :inset="item.inset"></v-divider>

                                                    <v-list-item v-else :key="item.title">
                                                        <v-list-item-avatar>
                                                            <v-img :src="item.avatar"></v-img>
                                                        </v-list-item-avatar>

                                                        <v-list-item-content>
                                                       <!--     <v-list-item-title v-html="item.title"></v-list-item-title>-->
                                                            <v-list-item-subtitle v-html="item.subtitle"></v-list-item-subtitle>
                                                        </v-list-item-content>
                                                    </v-list-item>
                                                </template>
                                            </v-list>
                                        </v-card>
                                        <!--댓글끝-->
                                        <v-row class="comment">
                                            <v-col cols="12" sm="12" md="12">
                                                <v-text-field
                                                    placeholder="댓글 쓰기..."
                                                    solo="solo"
                                                    rounded="rounded"
                                                    dense="dense"
                                                    color="black"></v-text-field>
                                            </v-col>
                                        </v-row>
                                    </v-container>
                                </v-form>
                            </v-card>

                        </v-flex>
                    </v-layout>
                </v-container>
            </v-dialog>
        </v-card>
</v-layout>
    </template>

    <script>
        export default {
            data: () => ({
                loading: false,
                selection: 1,
                items: [
                    {
                        avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
                        title: 'Brunch this weekend?',
                        subtitle: `<span class="text--primary">Ali Connors</span> &mdash; I'll be in your neighborhood doing errands this weekend. Do you want to hang out?`
                    }, {
                        divider: true,
                        inset: true
                    }, {
                        avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
                        title: 'Summer BBQ <span class="grey--text text--lighten-1">4</span>',
                        subtitle: `<span class="text--primary">to Alex, Scott, Jennifer</span> &mdash; Wish I could come, but I'm out of town this weekend.`
                    }, {
                        divider: true,
                        inset: true
                    }, {
                        avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
                        title: 'Oui oui',
                        subtitle: '<span class="text--primary">Sandra Adams</span> &mdash; Do you have Paris reco' +
                                'mmendations? Have you ever been?'
                    }, {
                        divider: true,
                        inset: true
                    }, {
                        avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
                        title: 'Birthday gift',
                        subtitle: '<span class="text--primary">Trevor Hansen</span> &mdash; Have any ideas about ' +
                                'what we should get Heidi for her birthday?'
                    }, {
                        divider: true,
                        inset: true
                    }
                ],
                lists: [
                    {
                        imglink:'https://cdn.vuetifyjs.com/images/cards/cooking.png',
                        title: '제목 1',
                        name: '서와닝',
                        category:'운동',
                        likes:34,
                        comment:4,
                        scraps:5
                    }, 
                    {
                        imglink:'https://cdn.pixabay.com/photo/2020/07/12/07/47/bee-5396362_1280.jpg',
                        title: '제목 2',
                        name: '지허니',
                        category:'음식',
                        likes:56,
                        comment:10,
                        scraps:10
                    }, 
                    {
                        imglink:'https://cdn.vuetifyjs.com/images/cards/sunshine.jpg',
                        title: '제목 3',
                        name: '잉구',
                        category:'취미',
                        likes:234,
                        comment:25,
                        scraps:45
                    }, 
                    {
                        imglink:'https://cdn.vuetifyjs.com/images/cards/docks.jpg',
                        title: '제목 4',
                        name: '엽이',
                        category:'기타',
                        likes:344,
                        comment:48,
                        scraps:53
                    }, 
                    {
                        imglink:'https://cdn.vuetifyjs.com/images/parallax/material.jpg',
                        title: '제목 5',
                        name: '화니',
                        category:'여행',
                        likes:734,
                        comment:64,
                        scraps:95
                    }, 
                ]
            }),

           methods: {
                detail() {
                    this.loading = true

                },
                follow(){

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
        .comment{
            padding-top: 15px;
            padding-bottom: 0px;
        }
    </style>